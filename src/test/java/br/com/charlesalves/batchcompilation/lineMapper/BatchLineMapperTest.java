package br.com.charlesalves.batchcompilation.lineMapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.Client;
import br.com.charlesalves.batchcompilation.domain.Sale;
import br.com.charlesalves.batchcompilation.domain.Salesman;
import br.com.charlesalves.batchcompilation.factories.DbcLineMapperFactory;
import br.com.charlesalves.batchcompilation.lineMappers.BatchLineMapper;

public class BatchLineMapperTest {

	private DbcLineMapperFactory factory = new DbcLineMapperFactory();
	private BatchLineMapper batchLineMapper = new BatchLineMapper(factory, "ç");

	@Test
	public void testSilesmanLine() throws Exception {
		String line = "001ç00000000001çPedroç50000";

		BatchData mappedLine = batchLineMapper.mapLine(line, 1);

		assertThat(mappedLine, is(instanceOf(Salesman.class)));
		Salesman salesman = (Salesman) mappedLine;

		assertThat(salesman.getCpf(), is(equalTo("00000000001")));
		assertThat(salesman.getName(), is(equalTo("Pedro")));
		assertThat(salesman.getSalary(), is(equalTo(50000D)));
	}

	@Test
	public void testClientLine() throws Exception {
		String line = "002ç00000000004çEduardo PereiraçRural";

		BatchData mappedLine = batchLineMapper.mapLine(line, 1);

		assertThat(mappedLine, is(instanceOf(Client.class)));
		Client salesman = (Client) mappedLine;

		assertThat(salesman.getCpf(), is(equalTo("00000000004")));
		assertThat(salesman.getName(), is(equalTo("Eduardo Pereira")));
		assertThat(salesman.getBusinessSite(), is(equalTo("Rural")));
	}

	@Test
	public void testSileLine() throws Exception {
		String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

		BatchData mappedLine = batchLineMapper.mapLine(line, 1);

		assertThat(mappedLine, is(instanceOf(Sale.class)));
		Sale sale = (Sale) mappedLine;

		assertThat(sale.getSaleId(), is(equalTo(10L)));
		assertThat(sale.getItems().size(), is(equalTo(3)));
		assertThat(sale.getSilesmanName(), is(equalTo("Pedro")));
		assertThat(sale.getAmount(), is(equalTo(1199D)));
	}
}
