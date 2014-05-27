package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.infoCenter.infra.ClienteDTO;

public class ClienteDAO {

	public void cadastrarCliente(ClienteDTO clienteDTO) throws SQLException {

		Connection conn = Conexao.getConexao();

		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement(
						"INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha)"
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, clienteDTO.getNome());
		ps.setString(2, clienteDTO.getTelefone());
		ps.setString(3, clienteDTO.getCpf());
		ps.setString(4, clienteDTO.getCep());
		ps.setString(5, clienteDTO.getDtNascimento());
		ps.setString(6, clienteDTO.getEndereco());
		ps.setString(7, clienteDTO.getEmail());
		ps.setString(8, clienteDTO.getLogin());
		ps.setString(9, clienteDTO.getSenha());

		ps.execute();
	}

	public void excluirCliente(ClienteDTO clienteDTO) throws SQLException {
		Connection conn = Conexao.getConexao();

		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");
		ps.setLong(1, clienteDTO.getIdCliente());
		ps.execute();
		
	}

	public ClienteDTO getClientePorId(long idCliente) throws SQLException {
		Connection conn = Conexao.getConexao();

		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT * FROM cliente WHERE id_cliente =?");

		ps.setLong(1, idCliente);
		ResultSet rs = ps.executeQuery();
		ClienteDTO clienteDTO = new ClienteDTO();
		if (rs.next()) {
			clienteDTO.setNome(rs.getString("nome"));
			clienteDTO.setTelefone(rs.getString("telefone"));
			clienteDTO.setCpf(rs.getString("cpf"));
			clienteDTO.setCep(rs.getString("cep"));
			clienteDTO.setDtNascimento(rs.getString("dt_nascimento"));
			clienteDTO.setEndereco(rs.getString("endereco"));
			clienteDTO.setEmail(rs.getString("email"));
			clienteDTO.setLogin(rs.getString("login"));
			clienteDTO.setSenha(rs.getString("senha"));
			clienteDTO.setIdCliente(rs.getLong("id_cliente"));
			clienteDTO.setAdministrador(rs.getBoolean("administrador"));
		}
		return clienteDTO;
	}

	public void alterarCliente(ClienteDTO clienteDTO) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("UPDATE cliente SET nome = ?, telefone = ?, cpf = ?, cep = ?, dt_nascimento = ?, endereco = ?, email = ?, login = ?, senha = ? "
						+ "WHERE id_cliente = ?");
		
		ps.setString(1, clienteDTO.getNome());
		ps.setString(2, clienteDTO.getTelefone());
		ps.setString(3, clienteDTO.getCpf());
		ps.setString(4, clienteDTO.getCep());
		ps.setString(5, clienteDTO.getDtNascimento());
		ps.setString(6, clienteDTO.getEndereco());
		ps.setString(7, clienteDTO.getEmail());
		ps.setString(8, clienteDTO.getLogin());
		ps.setString(9, clienteDTO.getSenha());
		ps.setLong(10, clienteDTO.getIdCliente());
		
		ps.execute();
	}

	public List<ClienteDTO> getClientes() throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT * FROM Cliente");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setNome(rs.getString("nome"));
			clienteDTO.setTelefone(rs.getString("telefone"));
			clienteDTO.setCpf(rs.getString("cpf"));
			clienteDTO.setCep(rs.getString("cep"));
			clienteDTO.setDtNascimento(rs.getString("dt_nascimento"));
			clienteDTO.setEndereco(rs.getString("endereco"));
			clienteDTO.setEmail(rs.getString("email"));
			clienteDTO.setLogin(rs.getString("login"));
			clienteDTO.setSenha(rs.getString("senha"));
			clienteDTO.setIdCliente(rs.getLong("id_cliente"));
			clienteDTO.setAdministrador(rs.getBoolean("administrador"));
			clientes.add(clienteDTO);
		}
		
		return clientes;
	}

	public ClienteDTO getClientePorLoginSenha(String usuario, String senha) {
		Connection conn = Conexao.getConexao();

		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT * FROM cliente WHERE login = ? AND senha = ?");

			ps.setString(1, usuario);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			ClienteDTO clienteDTO = new ClienteDTO();
			rs.next();
			clienteDTO.setNome(rs.getString("nome"));
			clienteDTO.setTelefone(rs.getString("telefone"));
			clienteDTO.setCpf(rs.getString("cpf"));
			clienteDTO.setCep(rs.getString("cep"));
			clienteDTO.setDtNascimento(rs.getString("dt_nascimento"));
			clienteDTO.setEndereco(rs.getString("endereco"));
			clienteDTO.setEmail(rs.getString("email"));
			clienteDTO.setLogin(rs.getString("login"));
			clienteDTO.setSenha(rs.getString("senha"));
			clienteDTO.setIdCliente(rs.getLong("id_cliente"));
			clienteDTO.setAdministrador(rs.getBoolean("administrador"));
		
			return clienteDTO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ClienteDTO> getClientesPorNomeCPF(ClienteDTO clientePesquisa) throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT * FROM Cliente WHERE UPPER(nome) LIKE UPPER(?) AND UPPER(cpf) LIKE UPPER(?)");
		ps.setString(1, '%' + clientePesquisa.getNome() + '%');
		ps.setString(2, '%' + clientePesquisa.getCpf() + '%');
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setNome(rs.getString("nome"));
			clienteDTO.setTelefone(rs.getString("telefone"));
			clienteDTO.setCpf(rs.getString("cpf"));
			clienteDTO.setCep(rs.getString("cep"));
			clienteDTO.setDtNascimento(rs.getString("dt_nascimento"));
			clienteDTO.setEndereco(rs.getString("endereco"));
			clienteDTO.setEmail(rs.getString("email"));
			clienteDTO.setLogin(rs.getString("login"));
			clienteDTO.setSenha(rs.getString("senha"));
			clienteDTO.setIdCliente(rs.getLong("id_cliente"));
			clienteDTO.setAdministrador(rs.getBoolean("administrador"));
			clientes.add(clienteDTO);
		}
		return clientes;

	}

	public boolean existeCpf(String cpf) {
		Connection conn = Conexao.getConexao();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT count(*) FROM cliente WHERE cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return (rs.getInt(1) > 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}