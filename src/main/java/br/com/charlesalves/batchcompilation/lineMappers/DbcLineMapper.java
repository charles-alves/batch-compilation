package br.com.charlesalves.batchcompilation.lineMappers;

import br.com.charlesalves.batchcompilation.domain.BatchData;

public interface DbcLineMapper {

	BatchData map(String[] line);

}
