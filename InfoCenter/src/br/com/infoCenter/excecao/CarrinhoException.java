package br.com.infoCenter.excecao;

public class CarrinhoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CarrinhoException() {
		super();
	}
	
	public CarrinhoException(String mensagem) {
		super(mensagem);
	}
}
