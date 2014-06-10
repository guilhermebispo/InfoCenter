package br.com.infoCenter.negocio;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.excecao.LoginException;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.persistencia.ClienteDAO;

public class LoginBO {

	private ClienteDAO clienteDAO;

	public LoginBO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public ClienteDTO logarUsuario(ClienteDTO clienteDTO) throws SQLException, LoginException{
		ClienteDTO usuario = clienteDAO.getClientePorLoginSenha(clienteDTO.getLogin(), clienteDTO.getSenha());
		return usuario;
	}

	public ClienteDTO buscarUsuarioLogado(HttpServletRequest req) {
		HttpSession sessao = req.getSession();
		ClienteDTO usuarioLogado = (ClienteDTO) sessao.getAttribute("usuarioLogado");
		return usuarioLogado;
	}

}
