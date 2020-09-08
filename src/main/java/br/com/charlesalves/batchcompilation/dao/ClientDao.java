package br.com.charlesalves.batchcompilation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.charlesalves.batchcompilation.domain.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

	@Override
	@Query("select count(distinct c.cpf) from Client c")
	long count();

}
