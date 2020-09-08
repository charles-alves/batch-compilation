package br.com.charlesalves.batchcompilation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "salespeople")
public class Salesman extends BatchData {

	@Column(name = "cpf", length = 30)
	private String cpf;

	@Column(name = "name", length = 150)
	private String name;

	@Column(name = "salary", nullable = false)
	private double salary;

	public Salesman() {
	}

	public Salesman(String cpf, String name, double salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

}
