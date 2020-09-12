package br.com.charlesalves.batchcompilation.tasklets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.charlesalves.batchcompilation.dao.ClientDao;
import br.com.charlesalves.batchcompilation.dao.SaleDao;
import br.com.charlesalves.batchcompilation.dao.SalesmanDao;
import br.com.charlesalves.batchcompilation.dao.dto.SalesReduceDto;
import br.com.charlesalves.batchcompilation.domain.Sale;
import br.com.charlesalves.batchcompilation.tasklets.dto.ExportDataDto;

@Component
public class ExportFileTasklet implements Tasklet {

	private ClientDao clientDao;
	private SaleDao saleDao;
	private SalesmanDao salesmanDao;
	private String outputPath;
	private String separator;

	public ExportFileTasklet(ClientDao clientDao, SaleDao saleDao, SalesmanDao salesmanDao,
			@Value("${file.output-path}") String outputPath, @Value("${file.csv-separator}") String separator) {
		this.clientDao = clientDao;
		this.saleDao = saleDao;
		this.salesmanDao = salesmanDao;
		this.outputPath = outputPath;
		this.separator = separator;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ExportDataDto exportData = createExportData();
		String line = exportData.toString(separator);
		exportData(line);

		return RepeatStatus.FINISHED;
	}

	private ExportDataDto createExportData() {
		long clients = clientDao.count();
		long salespeople = salesmanDao.count();
		Long bestSaleId = getBestSaleId();
		String worseSalesmanName = getWorseSalesmanName();

		return new ExportDataDto(clients, salespeople, bestSaleId, worseSalesmanName);
	}

	private Long getBestSaleId() {
		Optional<Sale> bestSaleOpt = saleDao.findFirstByOrderByAmountDesc();

		return bestSaleOpt.map(Sale::getSaleId).orElse(null);
	}

	private String getWorseSalesmanName() {
		Optional<SalesReduceDto> worseSalesmanOpt = saleDao.findFirstSalesmanOrderBySumAmountAsc();

		return worseSalesmanOpt.map(SalesReduceDto::getSalesmanName).orElse("");
	}

	private void exportData(String line) {
		Path path = Paths.get(outputPath);

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			writer.write(line);
		} catch (IOException e) {
		}
	}
}
