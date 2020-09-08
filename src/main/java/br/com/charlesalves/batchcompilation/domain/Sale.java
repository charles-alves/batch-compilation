package br.com.charlesalves.batchcompilation.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale extends BatchData {

	@Column(name = "saleId", nullable = false)
	private Long saleId;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "sale_id")
	private List<SaleItem> items;

	@Column(name = "silesman_name", nullable = false)
	private String silesmanName;

	@Column(name = "amount", nullable = false)
	private double amount;

	public Sale() {
	}

	public Sale(Long saleId, List<SaleItem> items, String silesmanName) {
		this.saleId = saleId;
		setItems(items);
		this.silesmanName = silesmanName;
	}

	public Long getSaleId() {
		return saleId;
	}

	private void setItems(List<SaleItem> items) {
		this.items = new ArrayList<>(items);

		items.forEach(i -> i.setSale(this));
		amount = items.stream()
			.mapToDouble(i -> i.getPrice() * i.getQuantity())
			.sum();
	}

	public List<SaleItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public String getSilesmanName() {
		return silesmanName;
	}

	public double getAmount() {
		return amount;
	}

}
