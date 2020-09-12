package br.com.charlesalves.batchcompilation.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.charlesalves.batchcompilation.domain.BatchData;

@Configuration
public class WriterConfig {

	@Bean("importWriter")
	public JpaItemWriter<BatchData> importWriter(EntityManagerFactory entityManagerFactory) {
		return new JpaItemWriterBuilder<BatchData>()
			.entityManagerFactory(entityManagerFactory)
			.build();
	}

}
