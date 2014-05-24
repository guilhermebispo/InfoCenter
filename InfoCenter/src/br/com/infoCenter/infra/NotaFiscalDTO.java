package br.com.infoCenter.infra;

public class NotaFiscalDTO {
	long numPedido;
	String dtCompra;
	long idCliente;
	String nomeCliente;
	double total;
	
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public long getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(long numPedido) {
		this.numPedido = numPedido;
	}
	public String getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
