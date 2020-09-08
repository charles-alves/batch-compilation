package br.com.charlesalves.batchcompilation.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.charlesalves.batchcompilation.dao.dto.SalesReduceDto;
import br.com.charlesalves.batchcompilation.domain.Sale;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/sale-insert-data.sql")
@Sql(scripts = "/clean-up-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class SaleDaoTest {

	@Autowired
	private SaleDao saleDao;

	@Test
	public void testBestSaleQuery() {
		Optional<Sale> saleOpt = saleDao.findFirstByOrderByAmountDesc();

		assertThat(saleOpt.isPresent(), is(true));
		Sale sale = saleOpt.get();
		assertThat(sale.getSaleId(), is(equalTo(2L)));
	}

	@Test
	public void testBestSaleQueryEmptyDatabase() {
		saleDao.deleteAll();
		Optional<Sale> saleOpt = saleDao.findFirstByOrderByAmountDesc();

		assertThat(saleOpt.isPresent(), is(false));
	}

	@Test
	public void testWorseSalesmanQuery() {
		Optional<SalesReduceDto> saleOpt = saleDao.findFirstSalesmanOrderBySumAmountAsc();

		assertThat(saleOpt.isPresent(), is(true));
		SalesReduceDto sale = saleOpt.get();
		assertThat(sale.getSalesmanName(), is(equalTo("Margarida")));
	}

	@Test
	public void testWorseSalesmanQueryEmptyDatabase() {
		saleDao.deleteAll();
		Optional<SalesReduceDto> saleOpt = saleDao.findFirstSalesmanOrderBySumAmountAsc();

		assertThat(saleOpt.isPresent(), is(false));
	}

}
