package br.com.charlesalves.batchcompilation.lineMappers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.charlesalves.batchcompilation.domain.BatchData;
import br.com.charlesalves.batchcompilation.factories.DbcLineMapperFactory;

@Component
public class BatchLineMapper implements LineMapper<BatchData> {

	private DbcLineMapperFactory lineMapperFactiryfactory;
	private String separator;

	public BatchLineMapper(
		DbcLineMapperFactory factory,
		@Value("${file.csv-separator}") String separator
	) {
		this.lineMapperFactiryfactory = factory;
		this.separator = separator;
	}

	@Override
	public BatchData mapLine(String line, int lineNumber) throws Exception {
		DbcLineMapper lineMapper = lineMapperFactiryfactory.forLine(line);
		String[] data = StringUtils.split(line, separator);

		return lineMapper.map(data);
	}
}
