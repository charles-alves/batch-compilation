package br.com.charlesalves.batchcompilation.dao;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.charlesalves.batchcompilation.dao.dto.SalesReduceDto;

@Repository
public class SaleCustomDaoImpl implements SaleCustomDao {

	private EntityManager entityManager;

	public SaleCustomDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public Optional<SalesReduceDto> findFirstSalesmanOrderBySumAmountAsc() {
		return entityManager.createQuery("select new br.com.charlesalves.batchcompilation.dao.dto.SalesReduceDto(s.silesmanName, sum(s.amount)) from Sale s group by s.silesmanName order by sum(s.amount) asc", SalesReduceDto.class)
			.setMaxResults(1)
			.getResultStream()
			.findFirst();
	}

}
