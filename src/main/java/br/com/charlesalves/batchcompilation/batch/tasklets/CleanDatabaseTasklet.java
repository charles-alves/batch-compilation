package br.com.charlesalves.batchcompilation.batch.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import br.com.charlesalves.batchcompilation.dao.BachDataDao;

@Component
public class CleanDatabaseTasklet implements Tasklet {

	private BachDataDao bachDataDao;

	public CleanDatabaseTasklet(BachDataDao bachDataDao) {
		this.bachDataDao = bachDataDao;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		bachDataDao.deleteAll();

		return RepeatStatus.FINISHED;
	}

}
