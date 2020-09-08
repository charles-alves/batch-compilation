package br.com.charlesalves.batchcompilation.dao.dto;

public class SalesReduceDto {

	private String salesmanName;
	private double salesAmount;

	public SalesReduceDto(String salesmanName, double salesAmount) {
		this.salesmanName = salesmanName;
		this.salesAmount = salesAmount;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	double getSalesAmount() {
		return salesAmount;
	}

}
