package br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.DbcLineMapper;
import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.domain.Sale;
import br.com.charlesalves.batchcompilation.domain.SaleItem;

public class SaleLineMapper implements DbcLineMapper {

	private static final int SALE_ID_INDEX = 1;
	private static final int SALE_ITEMS_INDEX = 2;
	private static final int SILESMAN_NAME_INDEX = 3;

	private static final int ITEM_ID_INDEX = 0;
	private static final int QUANTITY_ID_INDEX = 1;
	private static final int PRICE_INDEX = 2;

	@Override
	public BatchData map(String[] line) {
		Long saleId = NumberUtils.toLong(line[SALE_ID_INDEX]);
		List<SaleItem> items = createSaleItems(line[SALE_ITEMS_INDEX]);
		String silesmanName = line[SILESMAN_NAME_INDEX];

		return new Sale(saleId, items, silesmanName);
	}

	private List<SaleItem> createSaleItems(String salesItemsStr) {
		String sanitizedItems = sanitize(salesItemsStr);
		String[] items = sanitizedItems.split(",");

		return Arrays.stream(items)
			.map(this::createSaleItem)
			.collect(Collectors.toList());
	}

	private SaleItem createSaleItem(String itemStr) {
		String[] item = itemStr.split("-");

		Long itemId = NumberUtils.toLong(item[ITEM_ID_INDEX]);
		int quantity = NumberUtils.toInt(item[QUANTITY_ID_INDEX]);
		double price = NumberUtils.toDouble(item[PRICE_INDEX]);

		return new SaleItem(itemId, quantity, price);
	}

	private String sanitize(String salesItemsStr) {
		return salesItemsStr.replaceAll("\\[(.+)\\]", "$1").trim();
	}

}
