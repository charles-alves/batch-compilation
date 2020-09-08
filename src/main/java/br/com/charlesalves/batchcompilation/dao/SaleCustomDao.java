package br.com.charlesalves.batchcompilation.dao;

import java.util.Optional;

import br.com.charlesalves.batchcompilation.dao.dto.SalesReduceDto;

public interface SaleCustomDao {

	Optional<SalesReduceDto> findFirstSalesmanOrderBySumAmountAsc();

}
