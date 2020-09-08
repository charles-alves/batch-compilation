package br.com.charlesalves.batchcompilation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends BatchData {

	@Column(name = "cpf", length = 30)
	private String cpf;

	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@Column(name = "business_site", nullable = false, length = 80)
	private String businessSite;

	public Client() {
	}

	public Client(String cpf, String name, String businessSite) {
		this.cpf = cpf;
		this.name = name;
		this.businessSite = businessSite;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public String getBusinessSite() {
		return businessSite;
	}

}
