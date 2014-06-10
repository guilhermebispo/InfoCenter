package br.com.infoCenter.infra;

public class ProdutoDTO {

	private int codBarra;
	private String descricao;
	private String dtFabricacao;
	private String marca;
	private String obs; 
	private int qtdEstoque;
	private double valorProduto;
	private long idProduto;
	private int qtdItensCarrinho;
	private double valorTotalProduto;
	
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public int getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(int codBarra) {
		this.codBarra = codBarra;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDtFabricacao() {
		return dtFabricacao;
	}
	public void setDtFabricacao(String dtFabricacao) {
		this.dtFabricacao = dtFabricacao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	public int getQtdItensCarrinho() {
		return qtdItensCarrinho;
	}
	public void setQtdItensCarrinho(int qtdItensCarrinho) {
		this.qtdItensCarrinho = qtdItensCarrinho;
	}
	public double getValorTotalProduto() {
		return valorTotalProduto;
	}
	public void setValorTotalProduto(double valorTotalProduto) {
		this.valorTotalProduto = valorTotalProduto;
	}
}
