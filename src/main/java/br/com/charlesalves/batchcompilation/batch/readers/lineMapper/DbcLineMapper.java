package br.com.charlesalves.batchcompilation.batch.readers.lineMapper;

import br.com.charlesalves.batchcompilation.domain.BatchData;

public interface DbcLineMapper {

	BatchData map(String[] line);

}
