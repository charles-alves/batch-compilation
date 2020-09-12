package br.com.charlesalves.batchcompilation.tasklets.dto;

public class ExportDataDto {

	private long clientsCount;

	private long salespeopleCount;

	private Long bestSaleId;

	private String worseSalesman;

	public ExportDataDto(long clientsCount, long salespeopleCount, Long bestSaleId, String worseSalesman) {
		this.clientsCount = clientsCount;
		this.salespeopleCount = salespeopleCount;
		this.bestSaleId = bestSaleId;
		this.worseSalesman = worseSalesman;
	}

	public long getClientsCount() {
		return clientsCount;
	}

	public void setClientsCount(long clientsCount) {
		this.clientsCount = clientsCount;
	}

	public long getSalespeopleCount() {
		return salespeopleCount;
	}

	public void setSalespeopleCount(long salespeopleCount) {
		this.salespeopleCount = salespeopleCount;
	}

	public Long getBestSaleId() {
		return bestSaleId;
	}

	public void setBestSaleId(Long bestSaleId) {
		this.bestSaleId = bestSaleId;
	}

	public String getWorseSalesman() {
		return worseSalesman;
	}

	public void setWorseSalesman(String worseSalesman) {
		this.worseSalesman = worseSalesman;
	}

	public String toString(String separator) {
		return new StringBuilder()
			.append(clientsCount)
			.append(separator)
			.append(salespeopleCount)
			.append(separator)
			.append(bestSaleId)
			.append(separator)
			.append(worseSalesman)
			.toString();
	}
}
