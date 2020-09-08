package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.DbcLineMapper;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.Client;

public class ClientLineMapper implements DbcLineMapper {

	private static final int CPF_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int BUSINESS_SITE_INDEX = 3;

	@Override
	public BatchData map(String[] line) {
		String cpf = line[CPF_INDEX];
		String name = line[NAME_INDEX];
		String businessSite = line[BUSINESS_SITE_INDEX];

		return new Client(cpf, name, businessSite);
	}

}
