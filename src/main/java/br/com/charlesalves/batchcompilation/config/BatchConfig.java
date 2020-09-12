package br.com.charlesalves.batchcompilation.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.charlesalves.batchcompilation.dao.BachDataDao;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.UnvalidBatchData;
import br.com.charlesalves.batchcompilation.tasklets.CleanDatabaseTasklet;
import br.com.charlesalves.batchcompilation.tasklets.ExportFileTasklet;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean("importFileStep")
	public Step importFileStep(
		StepBuilderFactory stepBuilderFactory,
		MultiResourceItemReader<BatchData> reader,
		BachDataDao bachDataDao
	) {
		return stepBuilderFactory.get("importFileStep")
			.<BatchData, BatchData>chunk(100)
			.reader(reader)
			.processor((ItemProcessor<BatchData, BatchData>) item -> !(item instanceof UnvalidBatchData) ? item : null)
			.writer(bachDataDao::saveAll)
			.build();
	}

	@Bean("exportFileStep")
	public Step exportFileStep(
		StepBuilderFactory stepBuilderFactory,
		ExportFileTasklet exportFileTasklet
	) {
		return stepBuilderFactory.get("exportFileTasklet")
			.tasklet(exportFileTasklet)
			.build();
	}

	@Bean("cleanDatabaseStep")
	public Step cleanDatabaseStep(
		StepBuilderFactory stepBuilderFactory,
		CleanDatabaseTasklet cleanDatabaseTasklet
	) {
		return stepBuilderFactory.get("cleanTasklet")
			.tasklet(cleanDatabaseTasklet)
			.build();
	}

	@Bean
	public Job importMensagensJob(
		JobBuilderFactory jobBuilderFactory,
		@Qualifier("importFileStep") Step importFileStep,
		@Qualifier("exportFileStep") Step exportFileStep,
		@Qualifier("cleanDatabaseStep") Step cleanDatabaseStep
	) {
		return jobBuilderFactory.get("importMensagensJob")
			.incrementer(new RunIdIncrementer())
			.flow(importFileStep)
			.next(exportFileStep)
			.next(cleanDatabaseStep)
			.end()
			.build();
	}
}
