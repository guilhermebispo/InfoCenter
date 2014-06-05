package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.excecao.LoginException;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.negocio.LoginBO;
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
		
		LoginBO loginBO = new LoginBO(new ClienteDAO());
		ClienteDTO clienteDTO = buscarUsuarioDTOPorRequest(req);
		
		try {
			
			if (acao.equals("logar")) {
				
				try{
					ClienteDTO usuarioLogado = loginBO.logarUsuario(clienteDTO);
	
					HttpSession sessao = req.getSession();
					sessao.setAttribute("usuarioLogado", usuarioLogado);
					req.getRequestDispatcher("venda?acao=listar").forward(req, resp);
				} catch (LoginException loginException) {
					req.setAttribute("msg_erro", loginException.getMessage());
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}

			} else if (acao.equals("sair")) { 
				HttpSession sessao = req.getSession();
				sessao.removeAttribute("usuarioLogado");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}

		} catch (SQLException sqlException) {
			resp.sendRedirect("erro.jsp");
		}
	}

	private ClienteDTO buscarUsuarioDTOPorRequest (HttpServletRequest req){
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setLogin(req.getParameter("usuario"));
		clienteDTO.setSenha(req.getParameter("senha"));
		return clienteDTO;
	}

}