package br.com.charlesalves.batchcompilation.batch.readers.lineMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.charlesalves.batchcompilation.domain.BatchData;

@Component
public class BatchLineMapper implements LineMapper<BatchData> {

	private DbcLineMapperFactory factory;
	private String separator;

	public BatchLineMapper(DbcLineMapperFactory factory, @Value("${file.csv-separator}") String separator) {
		this.factory = factory;
		this.separator = separator;
	}

	@Override
	public BatchData mapLine(String line, int lineNumber) throws Exception {
		DbcLineMapper lineMapper = factory.forLine(line);
		String[] data = StringUtils.split(line, separator);

		return lineMapper.map(data);
	}
}
