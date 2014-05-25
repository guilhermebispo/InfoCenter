package br.com.infoCenter.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.persistencia.ClienteDAO;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String acao = req.getParameter("acao");
		ClienteDTO clienteDTO = new ClienteDTO();
		ClienteDAO clienteDAO = new ClienteDAO();
		if (acao.equals("logar")) {
			String usuario = req.getParameter("usuario");
			String senha = req.getParameter("senha");
			clienteDTO = clienteDAO.getClientePorLoginSenha(usuario, senha);
			if (clienteDTO != null) {
				HttpSession sessao = req.getSession();
				sessao.setAttribute("usuarioLogado", clienteDTO);
				req.getRequestDispatcher("venda?acao=listar")
						.forward(req, resp);
			} else {
				req.setAttribute("msg_erro", "Usuário ou senha inválidos!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} else if (acao.equals("sair")) { 
			HttpSession sessao = req.getSession();
			sessao.removeAttribute("usuarioLogado");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}