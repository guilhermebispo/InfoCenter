package br.com.infoCenter.excecao;

public class ClienteException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteException() {
		super();
	}
	
	public ClienteException(String mensagem) {
		super(mensagem);
	}
}
