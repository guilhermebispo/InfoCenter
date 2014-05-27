package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.infoCenter.infra.ProdutoDTO;

public class ProdutoDAO {

	public void cadastrarProduto(ProdutoDTO produtoDTO) throws SQLException {

		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement(
						"INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setLong(1, produtoDTO.getCodBarra());
		ps.setString(2, produtoDTO.getDescricao());
		ps.setString(3, produtoDTO.getDtFabricacao());
		ps.setString(4, produtoDTO.getMarca());
		ps.setString(5, produtoDTO.getObs());
		ps.setInt(6, produtoDTO.getQtdEstoque());
		ps.setDouble(7, produtoDTO.getValorProduto());

		ps.execute();

	}

	public List<ProdutoDTO> getProdutos() throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT id_produto, codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor "
						+ " FROM produto");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ProdutoDTO produtoDTO = new ProdutoDTO();

			produtoDTO.setCodBarra(rs.getInt("codigo_barra"));
			produtoDTO.setDescricao(rs.getString("descricao"));
			produtoDTO.setDtFabricacao(rs.getString("dt_fabricacao"));
			produtoDTO.setMarca(rs.getString("marca"));
			produtoDTO.setObs(rs.getString("observacao"));
			produtoDTO.setQtdEstoque(rs.getInt("qtd_estoque"));
			produtoDTO.setValorProduto(rs.getDouble("valor"));
			produtoDTO.setIdProduto(rs.getInt("id_produto"));
			produtoDTO.setValorTotalProduto(produtoDTO.getValorProduto()
					* produtoDTO.getQtdItensCarrinho());

			produtoDTOs.add(produtoDTO);
		}
		return produtoDTOs;
	}

	public List<ProdutoDTO> getProdutosPorCliente(long idUsuarioLogado) {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT produto.id_produto, codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor, qtd_produto"
							+ " FROM produto LEFT OUTER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND id_cliente = ? AND pago = false");
			ps.setLong(1, idUsuarioLogado);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProdutoDTO produtoDTO = new ProdutoDTO();

				produtoDTO.setCodBarra(rs.getInt("codigo_barra"));
				produtoDTO.setDescricao(rs.getString("descricao"));
				produtoDTO.setDtFabricacao(rs.getString("dt_fabricacao"));
				produtoDTO.setMarca(rs.getString("marca"));
				produtoDTO.setObs(rs.getString("observacao"));
				produtoDTO.setQtdEstoque(rs.getInt("qtd_estoque"));
				produtoDTO.setValorProduto(rs.getDouble("valor"));
				produtoDTO.setIdProduto(rs.getInt("id_produto"));
				produtoDTO.setQtdItensCarrinho(rs.getInt("qtd_produto"));
				produtoDTO.setValorTotalProduto(produtoDTO.getValorProduto()
						* produtoDTO.getQtdItensCarrinho());

				produtoDTOs.add(produtoDTO);
			}
			return produtoDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluirProduto(ProdutoDTO produtoDTO) throws SQLException {

		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement)
		conn.prepareStatement("DELETE FROM produto WHERE id_produto = ?");
		ps.setLong(1, produtoDTO.getIdProduto());
		ps.execute();

	}

	public ProdutoDTO getProdutoPorId(long idProduto) throws SQLException {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor, id_produto "
						+ "FROM produto WHERE id_produto =?");
		ps.setLong(1, idProduto);
		ResultSet rs = ps.executeQuery();
		ProdutoDTO produtoDTO = new ProdutoDTO();

		if (rs.next()) {
			produtoDTO.setCodBarra(rs.getInt(1));
			produtoDTO.setDescricao(rs.getString(2));
			produtoDTO.setDtFabricacao(rs.getString(3));
			produtoDTO.setMarca(rs.getString(4));
			produtoDTO.setObs(rs.getString(5));
			produtoDTO.setQtdEstoque(rs.getInt(6));
			produtoDTO.setValorProduto(rs.getDouble(7));
			produtoDTO.setIdProduto(rs.getInt(8));
		}

		return produtoDTO;
	}

	public void alterarProduto(ProdutoDTO produtoDTO) throws SQLException {

		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("UPDATE produto SET codigo_barra = ?, descricao = ?, dt_fabricacao = ?,  qtd_estoque = ?,"
						+ " marca = ?, observacao = ?, valor = ? WHERE id_produto = ?");

		ps.setInt(1, produtoDTO.getCodBarra());
		ps.setString(2, produtoDTO.getDescricao());
		ps.setString(3, produtoDTO.getDtFabricacao());
		ps.setInt(4, produtoDTO.getQtdEstoque());
		ps.setString(5, produtoDTO.getMarca());
		ps.setString(6, produtoDTO.getObs());
		ps.setDouble(7, produtoDTO.getValorProduto());
		ps.setLong(8, produtoDTO.getIdProduto());

		ps.execute();
	}

	public List<ProdutoDTO> getProdutosPorDescMarca(ProdutoDTO produtoPesquisado) throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT id_produto, codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor "
						+ " FROM produto WHERE UPPER(descricao) LIKE UPPER(?) AND UPPER(marca) LIKE UPPER(?)");
		ps.setString(1, "%" + produtoPesquisado.getDescricao() + "%");
		ps.setString(2, "%" + produtoPesquisado.getMarca() + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ProdutoDTO produtoDTO = new ProdutoDTO();

			produtoDTO.setCodBarra(rs.getInt("codigo_barra"));
			produtoDTO.setDescricao(rs.getString("descricao"));
			produtoDTO.setDtFabricacao(rs.getString("dt_fabricacao"));
			produtoDTO.setMarca(rs.getString("marca"));
			produtoDTO.setObs(rs.getString("observacao"));
			produtoDTO.setQtdEstoque(rs.getInt("qtd_estoque"));
			produtoDTO.setValorProduto(rs.getDouble("valor"));
			produtoDTO.setIdProduto(rs.getInt("id_produto"));

			produtoDTOs.add(produtoDTO);
		}
		return produtoDTOs;
	}

}