package br.com.charlesalves.batchcompilation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesalves.batchcompilation.domain.Sale;

public interface SaleDao extends JpaRepository<Sale, Long>, SaleCustomDao {

	Optional<Sale> findFirstByOrderByAmountDesc();

}
