package br.com.charlesalves.batchcompilation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales_items")
public class SaleItem extends BatchData {

	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;

	@Column(name = "item_id", nullable = false)
	private Long itemId;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "price", nullable = false)
	private double price;

	public SaleItem() {
	}

	public SaleItem(Long itemId, int quantity, double price) {
		this.itemId = itemId;
		this.quantity = quantity;
		this.price = price;
	}

	protected void setSale(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {
		return sale;
	}

	public Long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

}
