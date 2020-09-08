package br.com.charlesalves.batchcompilation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.charlesalves.batchcompilation.domain.Salesman;

public interface SalesmanDao extends JpaRepository<Salesman, Long> {

	@Override
	@Query("select count(distinct s.cpf) from Salesman s")
	long count();

}
