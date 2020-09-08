package br.com.charlesalves.batchcompilation.batch;

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

import br.com.charlesalves.batchcompilation.batch.tasklets.CleanDatabaseTasklet;
import br.com.charlesalves.batchcompilation.batch.tasklets.ExportFileTasklet;
import br.com.charlesalves.batchcompilation.dao.BachDataDao;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.UnvalidBatchData;

@Configuration
@EnableBatchProcessing
public class JobConfig {

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactory;

	public JobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean("importFileStep")
	public Step importFileStep(MultiResourceItemReader<BatchData> reader, BachDataDao bachDataDao) {
		return stepBuilderFactory.get("importFileStep")
			.<BatchData, BatchData>chunk(100)
			.reader(reader)
			.processor((ItemProcessor<BatchData, BatchData>) item -> !(item instanceof UnvalidBatchData) ? item : null)
			.writer(bachDataDao::saveAll)
			.allowStartIfComplete(true)
			.build();
	}

	@Bean("exportFileStep")
	public Step exportFileStep(ExportFileTasklet exportFileTasklet) {
		return stepBuilderFactory.get("exportFileTasklet")
			.tasklet(exportFileTasklet)
			.allowStartIfComplete(true)
			.build();
	}

	@Bean("cleanDatabaseStep")
	public Step cleanDatabaseStep(CleanDatabaseTasklet cleanDatabaseTasklet) {
		return stepBuilderFactory.get("cleanTasklet")
			.tasklet(cleanDatabaseTasklet)
			.allowStartIfComplete(true)
			.build();
	}

	@Bean
	public Job importMensagensJob(
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
