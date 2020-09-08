package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;

import br.com.charlesalves.batchcompilation.domain.Sale;
import br.com.charlesalves.batchcompilation.domain.SaleItem;

public class SaleLineMapperTest {

	@Test
	public void mapAVAlidSale() {
		SaleLineMapper saleLinerMapper = new SaleLineMapper();

		Sale sale = (Sale) saleLinerMapper.map(new String[]{"003", "10", "[1-10-100]", "Pedro"});

		assertThat(sale.getSaleId(), is(equalTo(10L)));
		assertThat(sale.getSilesmanName(), is(equalTo("Pedro")));
		assertThatItems(sale.getItems());
	}

	private void assertThatItems(List<SaleItem> items) {
		items.forEach(i -> {
			assertThat(i.getItemId(), is(equalTo(1L)));
			assertThat(i.getQuantity(), is(equalTo(10)));
			assertThat(i.getPrice(), is(equalTo(100D)));
		});
	}

}
