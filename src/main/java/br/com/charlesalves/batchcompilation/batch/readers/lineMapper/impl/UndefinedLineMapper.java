package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.DbcLineMapper;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.UnvalidBatchData;

public class UndefinedLineMapper implements DbcLineMapper {

	@Override
	public BatchData map(String[] line) {
		return new UnvalidBatchData();
	}

}
