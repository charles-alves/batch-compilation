package br.com.charlesalves.batchcompilation.batch.readers.lineMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.ClientLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.SaleLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.SalesmanLineMapper;
import br.com.charlesalves.batchcompilation.batch.readers.lineMapper.impl.UndefinedLineMapper;

@Component
public class DbcLineMapperFactory {

	private static final String SALESMAN_CODE = "001";
	private static final String CLIENT_CODE = "002";
	private static final String SALE_CODE = "003";

	Map<String, DbcLineMapper> mappers = new HashMap<String, DbcLineMapper>() {
		private static final long serialVersionUID = -2551526213720348038L;

		{
			put(SALESMAN_CODE, new SalesmanLineMapper());
			put(CLIENT_CODE, new ClientLineMapper());
			put(SALE_CODE, new SaleLineMapper());
		}
	};

	private static Pattern pattern = Pattern.compile("(\\d{3}).+");

	public DbcLineMapper forLine(String line) {
		String lineType = getLineType(line);

		return mappers.getOrDefault(lineType, new UndefinedLineMapper());
	}

	private String getLineType(String line) {
		if (StringUtils.isEmpty(line)) {
			return "";
		}

		Matcher matcher = pattern.matcher(line);
		boolean find = matcher.find();

		return find ? matcher.group(1) : "";
	}

}
