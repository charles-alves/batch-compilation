package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.DbcLineMapper;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.Salesman;

public class SalesmanLineMapper implements DbcLineMapper {

	private static final int CPF_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int SALARY_INDEX = 3;

	@Override
	public BatchData map(String[] line) {
		String cpf = line[CPF_INDEX];
		String name = line[NAME_INDEX];
		double salary = NumberUtils.toDouble(line[SALARY_INDEX]);

		return new Salesman(cpf, name, salary);
	}

}
