package br.com.charlesalves.batchcompilation.lineMappers.impl;

import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.UnvalidBatchData;
import br.com.charlesalves.batchcompilation.lineMappers.DbcLineMapper;

public class UndefinedLineMapper implements DbcLineMapper {

	@Override
	public BatchData map(String[] line) {
		return new UnvalidBatchData();
	}

}
