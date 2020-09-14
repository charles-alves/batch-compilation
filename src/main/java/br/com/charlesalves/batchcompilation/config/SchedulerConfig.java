package br.com.charlesalves.batchcompilation.config;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Profile("!test")
public class SchedulerConfig {

	private JobLauncher jobLauncher;
	private Job importMensagensJob;

	public SchedulerConfig(JobLauncher jobLauncher, Job importMensagensJob) {
		this.jobLauncher = jobLauncher;
		this.importMensagensJob = importMensagensJob;
	}

	@Scheduled(fixedRate = 60000)
	public void perform() throws Exception {
		JobParameters param = new JobParametersBuilder()
			.addString("jobId", UUID.randomUUID().toString())
			.toJobParameters();

		jobLauncher.run(importMensagensJob, param);
	}
}
