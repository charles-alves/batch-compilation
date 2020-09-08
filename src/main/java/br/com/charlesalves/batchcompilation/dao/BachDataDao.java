package br.com.charlesalves.batchcompilation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesalves.batchcompilation.domain.BatchData;

public interface BachDataDao
		extends JpaRepository<BatchData, Long> {

}
