package br.com.infoCenter.negocio;

import java.sql.SQLException;
import java.util.List;

import br.com.infoCenter.excecao.ClienteException;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.persistencia.ClienteDAO;

public class ClienteBO {

	private ClienteDAO clienteDAO;

	public ClienteBO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteException, SQLException{
		if(!validarSenha(clienteDTO)){
			throw new ClienteException("A confirmação da senha não confere!");
		} else if (clienteDAO.existeCpf(clienteDTO.getCpf())) { 
			throw new ClienteException("O cpf (" + clienteDTO.getCpf() + ") já cadastrado!");
		} else {
			clienteDAO.cadastrarCliente(clienteDTO);
		}
	}

	private boolean validarSenha(ClienteDTO clienteDTO){
		if (clienteDTO.getSenha().equals(clienteDTO.getConfirmacaoSenha())){
			return true;
		} else {
			return false;
		}
	}

	public List<ClienteDTO> listarClientes() throws SQLException {
		return clienteDAO.getClientes();
	}

	public void excluirCliente(ClienteDTO clienteDTO) throws SQLException {
		clienteDAO.excluirCliente(clienteDTO);
	}

	public ClienteDTO buscarClientePorId(long idCliente) throws SQLException {
		return clienteDAO.getClientePorId(idCliente);
	}

	public void concluirAlteracao(ClienteDTO clienteDTO) throws ClienteException, SQLException {
		if(!validarSenha(clienteDTO)){
			throw new ClienteException("A confirmação da senha não confere!");
		} else if (clienteDAO.existeCpf(clienteDTO.getCpf())) { 
			throw new ClienteException("O cpf (" + clienteDTO.getCpf() + ") já cadastrado!");
		} else {
			clienteDAO.alterarCliente(clienteDTO);
		}
	}

	public List<ClienteDTO> buscarClientesPorNomeCPF(ClienteDTO clienteDTO) throws SQLException {
		return clienteDAO.getClientesPorNomeCPF(clienteDTO);
	}
}
