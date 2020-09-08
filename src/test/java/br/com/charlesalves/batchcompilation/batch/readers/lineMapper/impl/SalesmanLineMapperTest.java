package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.domain.Salesman;

public class SalesmanLineMapperTest {

	@Test
	public void mapAVAlidSalesman() {
		SalesmanLineMapper salesmanLinerMapper = new SalesmanLineMapper();

		Salesman salesman = (Salesman) salesmanLinerMapper.map(new String[]{"001", "00000000000", "Jos� Pedro", "5000"});

		assertThat(salesman.getCpf(), is(equalTo("00000000000")));
		assertThat(salesman.getName(), is(equalTo("Jos� Pedro")));
		assertThat(salesman.getSalary(), is(equalTo(5000D)));
	}

}
