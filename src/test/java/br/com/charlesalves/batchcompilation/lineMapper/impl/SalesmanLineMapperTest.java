package br.com.charlesalves.batchcompilation.lineMapper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.domain.Salesman;
import br.com.charlesalves.batchcompilation.lineMappers.impl.SalesmanLineMapper;

public class SalesmanLineMapperTest {

	@Test
	public void mapVAlidSalesman() {
		SalesmanLineMapper salesmanLinerMapper = new SalesmanLineMapper();

		Salesman salesman = (Salesman) salesmanLinerMapper.map(new String[]{"001", "00000000000", "José Pedro", "5000"});

		assertThat(salesman.getCpf(), is(equalTo("00000000000")));
		assertThat(salesman.getName(), is(equalTo("José Pedro")));
		assertThat(salesman.getSalary(), is(equalTo(5000D)));
	}

}
