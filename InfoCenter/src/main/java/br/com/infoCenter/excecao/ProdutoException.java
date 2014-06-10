package br.com.infoCenter.excecao;

public class ProdutoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoException() {
		super();
	}
	
	public ProdutoException(String mensagem) {
		super(mensagem);
	}
}
