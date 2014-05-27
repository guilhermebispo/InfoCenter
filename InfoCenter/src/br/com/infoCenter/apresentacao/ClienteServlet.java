package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infoCenter.excecao.ClienteException;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.negocio.ClienteBO;
import br.com.infoCenter.persistencia.ClienteDAO;

public class ClienteServlet extends HttpServlet {

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
		ClienteBO clienteBO = new ClienteBO(new ClienteDAO());
		ClienteDTO clienteDTO;
		
		try{
		
			if (acao.equals("cadastrar")) {
				
				clienteDTO = getClienteDTObyRequest(req);
				try{
					clienteBO.cadastrarCliente(clienteDTO);
					resp.sendRedirect("sucesso.jsp");
				} catch (ClienteException clienteException){
					req.setAttribute("msg_erro", clienteException.getMessage());
					req.getRequestDispatcher("_cliente/cliente_cadastrar.jsp").forward(req, resp);
				}

			} else if (acao.equals("listar")) {
				
				req.setAttribute("clientes", clienteBO.listarClientes());
				req.getRequestDispatcher("_cliente/cliente_listar.jsp").forward(req, resp);
	
			} else if (acao.equals("excluir")) {
				
				clienteDTO = getClienteDTObyRequest(req);
				clienteBO.excluirCliente(clienteDTO);
				resp.sendRedirect("sucesso.jsp");
			
			} else if (acao.equals("alterar")) {
				
				clienteDTO = getClienteDTObyRequest(req);
				req.setAttribute("cliente", clienteBO.buscarClientePorId(clienteDTO.getIdCliente()));
				req.getRequestDispatcher("_cliente/cliente_alterar.jsp").forward(req, resp);
			
			} else if (acao.equals("concluirAlteracao")) {
	
				clienteDTO = getClienteDTObyRequest(req);
				try{
					clienteBO.concluirAlteracao(clienteDTO);
					resp.sendRedirect("sucesso.jsp");
				} catch (ClienteException clienteException){
					req.setAttribute("msg_erro", clienteException.getMessage());
					req.getRequestDispatcher("_cliente/cliente_cadastrar.jsp").forward(req, resp);
				}
				
			} else if (acao.equals("consultar")) {
				
				clienteDTO = getClienteDTObyRequest(req);
				req.setAttribute("clientes", clienteBO.buscarClientesPorNomeCPF(clienteDTO));
				req.getRequestDispatcher("_cliente/cliente_listar.jsp").forward(req, resp);
			}

		} catch (SQLException sqlException) {
			resp.sendRedirect("erro.jsp");
		}
	}
	
	private ClienteDTO getClienteDTObyRequest (HttpServletRequest req){
		ClienteDTO clienteDTO = new ClienteDTO();
		if (req.getParameter("idCliente") != null){
			clienteDTO.setIdCliente(Long.parseLong(req.getParameter("idCliente")));
		}
		clienteDTO.setNome(req.getParameter("nome"));
		clienteDTO.setEmail(req.getParameter("email"));
		clienteDTO.setDtNascimento(req.getParameter("dtNascimento"));
		clienteDTO.setEndereco(req.getParameter("endereco"));
		clienteDTO.setCep(req.getParameter("cep"));
		clienteDTO.setCpf(req.getParameter("cpf"));
		clienteDTO.setTelefone(req.getParameter("telefone"));
		clienteDTO.setLogin(req.getParameter("login"));
		clienteDTO.setSenha(req.getParameter("senha"));
		clienteDTO.setConfirmacaoSenha(req.getParameter("confirmarSenha"));
		return clienteDTO;
	}
}