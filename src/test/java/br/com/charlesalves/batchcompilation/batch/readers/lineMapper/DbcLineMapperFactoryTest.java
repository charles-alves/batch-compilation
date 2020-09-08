package br.com.charlesalves.batchcompilation.batch.readers.lineMapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.ClientLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.SaleLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.SalesmanLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.UndefinedLineMapper;

public class DbcLineMapperFactoryTest {

	DbcLineMapperFactory factory = new DbcLineMapperFactory();

	@Test
	public void testBuildClientLineMapper() {
		DbcLineMapper lineMapper = factory.forLine("002ç2345675434544345çJose da SilvaçRural");

		assertThat(lineMapper, is(instanceOf(ClientLineMapper.class)));
	}

	@Test
	public void testBuildSaleLineMapper() {
		DbcLineMapper lineMapper = factory.forLine("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");

		assertThat(lineMapper, is(instanceOf(SaleLineMapper.class)));
	}

	@Test
	public void testBuildSalesmanLineMapper() {
		DbcLineMapper lineMapper = factory.forLine("001ç1234567891234çPedroç50000");

		assertThat(lineMapper, is(instanceOf(SalesmanLineMapper.class)));
	}

	@Test
	public void testEmptyLine() {
		DbcLineMapper lineMapper = factory.forLine("");

		assertThat(lineMapper, is(instanceOf(UndefinedLineMapper.class)));
	}

	@Test
	public void testInvalidLineCode() {
		DbcLineMapper lineMapper = factory.forLine("004ç1234567891234çPedroç50000");

		assertThat(lineMapper, is(instanceOf(UndefinedLineMapper.class)));
	}

	@Test
	public void testNullValue() {
		DbcLineMapper lineMapper = factory.forLine(null);

		assertThat(lineMapper, is(instanceOf(UndefinedLineMapper.class)));
	}
}
