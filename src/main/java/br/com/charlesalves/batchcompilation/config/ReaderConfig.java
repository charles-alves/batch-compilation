package br.com.charlesalves.batchcompilation.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.lineMappers.BatchLineMapper;
import br.com.charlesalves.batchcompilation.util.IOUtils;

@Configuration
public class ReaderConfig {

	Logger logger = LoggerFactory.getLogger(ReaderConfig.class);

	private String inputDir;
	private String extension;

	public ReaderConfig(@Value("${file.inputdir}") String inputDir,
			@Value("${file.extension}") String extension) {
		this.inputDir = inputDir;
		this.extension = extension;
	}

	@Bean
	@StepScope
	public MultiResourceItemReader<BatchData> itemReader(FlatFileItemReader<BatchData> delegateReader) {
		return new MultiResourceItemReaderBuilder<BatchData>()
			.resources(getResources())
			.delegate(delegateReader)
			.name("itemReader")
			.build();
	}

	@Bean
	public FlatFileItemReader<BatchData> delegateReader(BatchLineMapper lineMapper) {
		return new FlatFileItemReaderBuilder<BatchData>()
			.lineMapper(lineMapper)
			.encoding("UTF-8")
			.name("delegateReader")
			.build();
	}

	private Resource[] getResources() {
		try {
			IOUtils.mkdirs(inputDir);
			return new PathMatchingResourcePatternResolver().getResources("file:" + inputDir + "/*" + extension);
		} catch (IOException e) {
			logger.warn("Não foi possível realizar a leitura dos arquivos de entrada", e);
			return new Resource[0];
		}
	}
}
