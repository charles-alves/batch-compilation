package br.com.charlesalves.batchcompilation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "batch_data_seq", sequenceName = "batch_data_seq")
public abstract class BatchData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_data_seq")
	private Long id;

	public BatchData() {
	}

	public Long getId() {
		return id;
	}
}
