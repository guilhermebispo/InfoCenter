package br.com.infoCenter.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.persistencia.ClienteDAO;

public class ClienteServlet extends HttpServlet {

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
		ClienteDAO clienteDao = new ClienteDAO();
		ClienteDTO clienteDTO = new ClienteDTO();
		
		if (acao.equals("cadastrar")) {
			clienteDTO.setNome(req.getParameter("nome"));
			clienteDTO.setEmail(req.getParameter("email"));
			clienteDTO.setDtNascimento(req.getParameter("dtNascimento"));
			clienteDTO.setEndereco(req.getParameter("endereco"));
			clienteDTO.setCep(req.getParameter("cep"));
			clienteDTO.setCpf(req.getParameter("cpf"));
			clienteDTO.setTelefone(req.getParameter("telefone"));
			clienteDTO.setLogin(req.getParameter("login"));
			clienteDTO.setSenha(req.getParameter("senha"));
			if(!validarSenha(req.getParameter("senha"), req.getParameter("confirmarSenha"))){
				req.setAttribute("msg_erro", "A confirmação da senha não confere!");
				req.getRequestDispatcher("_cliente/cliente_cadastrar.jsp").forward(req, resp);
			} else if (clienteDao.existeCpf(clienteDTO.getCpf())) { 
				req.setAttribute("msg_erro", "O cpf (" + clienteDTO.getCpf() + ") já cadastrado!");
				req.getRequestDispatcher("_cliente/cliente_cadastrar.jsp").forward(req, resp);
			} else if (clienteDao.cadastrarCliente(clienteDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}

		} else if (acao.equals("listar")) {
			req.setAttribute("clientes", clienteDao.getClientes());
			req.getRequestDispatcher("_cliente/cliente_listar.jsp").forward(req, resp);

		} else if (acao.equals("excluir")) {
			clienteDTO.setIdCliente(Long.parseLong(req.getParameter("idCliente")));
			if (clienteDao.excluirCliente(clienteDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}
		
		} else if (acao.equals("alterar")) {
			req.setAttribute("cliente", clienteDao.getClientePorId(Long
					.parseLong(req.getParameter("idCliente"))));
			req.getRequestDispatcher("_cliente/cliente_alterar.jsp").forward(req, resp);
		
		} else if (acao.equals("concluirAlteracao")) {
			clienteDTO.setIdCliente(Long.parseLong(req
					.getParameter("idCliente")));
			clienteDTO.setNome(req.getParameter("nome"));
			clienteDTO.setEmail(req.getParameter("email"));
			clienteDTO.setDtNascimento(req.getParameter("dtNascimento"));
			clienteDTO.setEndereco(req.getParameter("endereco"));
			clienteDTO.setCep(req.getParameter("cep"));
			clienteDTO.setCpf(req.getParameter("cpf"));
			clienteDTO.setTelefone(req.getParameter("telefone"));
			clienteDTO.setLogin(req.getParameter("login"));
			clienteDTO.setSenha(req.getParameter("senha"));
			if(!validarSenha(req.getParameter("senha"), req.getParameter("confirmarSenha"))){
				req.setAttribute("cliente", clienteDTO);
				req.setAttribute("msg_erro", "A confirmação da senha não confere!");
				req.getRequestDispatcher("_cliente/cliente_alterar.jsp").forward(req, resp);
			} else if (clienteDao.alterarCliente(clienteDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}

		} else if (acao.equals("consultar")) {
			clienteDTO.setNome(req.getParameter("nome"));
			clienteDTO.setCpf(req.getParameter("cpf"));
			req.setAttribute("clientes", clienteDao.getClientesPorNomeCPF(clienteDTO));
			req.getRequestDispatcher("_cliente/cliente_listar.jsp").forward(req, resp);
		}
	}
	
	public boolean validarSenha(String senha, String senhaConfirmada){
		if (senha.equals(senhaConfirmada)){
			return true;
		} else {
			return false;
		}
	}
}