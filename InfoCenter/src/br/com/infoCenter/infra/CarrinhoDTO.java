package br.com.infoCenter.infra;

public class CarrinhoDTO {

	private long idCarrinho;
	private long idCliente;
	private long idProduto;
	private String dtCompra;
	private int qtdProduto;
	private boolean pago;

	public long getIdCarrinho() {
		return idCarrinho;
	}
	public void setIdCarrinho(long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public String getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}
	public int getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
}
