package br.com.charlesalves.batchcompilation.batch.readers;

import java.io.IOException;

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

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.BatchLineMapper;
import br.com.charlesalves.batchcompilation.domain.BatchData;

@Configuration
public class ReaderConfiguration {

	private String inputDir;
	private String extension;

	public ReaderConfiguration(@Value("${file.inputdir}") String inputDir,
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
			return new PathMatchingResourcePatternResolver().getResources(inputDir + "/*" + extension);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
