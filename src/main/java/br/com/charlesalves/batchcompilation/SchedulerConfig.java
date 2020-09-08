package br.com.charlesalves.batchcompilation;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;

//@Configuration
//@EnableScheduling
public class SchedulerConfig {

	private JobLauncher jobLauncher;
	private Job importMensagensJob;

	public SchedulerConfig(JobLauncher jobLauncher, Job importMensagensJob) {
		this.jobLauncher = jobLauncher;
		this.importMensagensJob = importMensagensJob;
	}

	@Scheduled(cron = "0 */1 * * * *")
	public void perform() throws Exception {
		System.out.println("Job Started at :" + new Date());

		JobParameters param = new JobParametersBuilder().toJobParameters();

		JobExecution execution = jobLauncher.run(importMensagensJob, param);

		System.out.println("Job finished with status :" + execution.getStatus());
	}
}
