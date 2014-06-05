package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.infoCenter.infra.CarrinhoDTO;
import br.com.infoCenter.infra.ProdutoDTO;

public class CarrinhoDAO {

	public void incluirCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement ps;
		int qtd_produto = getQtdProduto(carrinhoDTO);
		
		if (qtd_produto == 0) {
			ps = (PreparedStatement) conn.prepareStatement("INSERT INTO carrinho (id_cliente, id_produto, dt_compra, qtd_produto, pago) "
					+ "VALUES (?, ?, ?, ?, ?)");
			ps.setLong(1, carrinhoDTO.getIdCliente());
			ps.setLong(2, carrinhoDTO.getIdProduto());
			ps.setString(3, carrinhoDTO.getDtCompra());
			ps.setLong(4, carrinhoDTO.getQtdProduto());
			ps.setBoolean(5, carrinhoDTO.isPago());
		} else {
			ps = (PreparedStatement) conn.prepareStatement("UPDATE carrinho SET dt_compra = ?, qtd_produto = ? "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setString(1, carrinhoDTO.getDtCompra());
			ps.setLong(2, carrinhoDTO.getQtdProduto() + qtd_produto);
			ps.setLong(3, carrinhoDTO.getIdCliente());
			ps.setLong(4, carrinhoDTO.getIdProduto());
			ps.setBoolean(5, carrinhoDTO.isPago());
		}

		ps.execute();
	}

	public int getQtdProduto(CarrinhoDTO carrinhoDTO) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement ps;
		ps = (PreparedStatement) conn.prepareStatement
				("SELECT sum(qtd_produto) FROM carrinho WHERE id_produto = ? AND id_cliente = ? AND pago = ?");
		ps.setLong(1, carrinhoDTO.getIdProduto());
		ps.setLong(2, carrinhoDTO.getIdCliente());
		ps.setBoolean(3, carrinhoDTO.isPago());
		ResultSet rs = ps.executeQuery();
		rs.next();
		int qtd_produto = rs.getInt(1);
		return qtd_produto;
	}

	public List<ProdutoDTO> getItensCarrinho(long idUsuario) throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT produto.id_produto, codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor, qtd_produto "
						+ "FROM produto INNER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND id_cliente = ? AND pago = false");
		ps.setLong(1, idUsuario);
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
			produtoDTO.setValorTotalProduto(produtoDTO.getQtdItensCarrinho()*produtoDTO.getValorProduto());

			produtoDTOs.add(produtoDTO);
		}
		return produtoDTOs;
	}

	public List<ProdutoDTO> getProdutosPagosPorNumPedido(long numPedido) {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT produto.id_produto, codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor, qtd_produto "
							+ "FROM produto INNER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND num_pedido = ? AND pago = true");
			ps.setLong(1, numPedido);
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
				produtoDTO.setValorTotalProduto(produtoDTO.getQtdItensCarrinho()*produtoDTO.getValorProduto());

				produtoDTOs.add(produtoDTO);
			}
			return produtoDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void apagarCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException {
		Connection conn = Conexao.getConexao();
		int qtd_produto = getQtdProduto(carrinhoDTO);
		PreparedStatement ps;
		
		if (qtd_produto > 1){
			ps = (PreparedStatement) conn.prepareStatement("UPDATE carrinho SET dt_compra = ?, qtd_produto = ? "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setString(1, carrinhoDTO.getDtCompra());
			ps.setLong(2, qtd_produto - carrinhoDTO.getQtdProduto());
			ps.setLong(3, carrinhoDTO.getIdCliente());
			ps.setLong(4, carrinhoDTO.getIdProduto());
			ps.setBoolean(5, carrinhoDTO.isPago());
			
		} else {
			ps = (PreparedStatement) conn.prepareStatement("DELETE FROM carrinho "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setLong(1, carrinhoDTO.getIdCliente());
			ps.setLong(2, carrinhoDTO.getIdProduto());
			ps.setBoolean(3, carrinhoDTO.isPago());
		}
		
		ps.execute();
	}
	public long efetuarCompra(long idCliente, String stringFormatadaDtCompra) {
		Connection conn = Conexao.getConexao();
		try {
			PreparedStatement psCarrinho = (PreparedStatement) conn
					.prepareStatement("SELECT id_carrinho, id_cliente, id_produto, qtd_produto FROM carrinho WHERE id_cliente = ? AND pago = false");
			psCarrinho.setLong(1, idCliente);
			ResultSet rsCarrinho = psCarrinho.executeQuery();

			long proximoPedido = getUltimoPedido() + 1;
			while (rsCarrinho.next()) {
				CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
				carrinhoDTO.setIdCarrinho(rsCarrinho.getLong("id_carrinho"));
				carrinhoDTO.setIdCliente(rsCarrinho.getLong("id_cliente"));
				carrinhoDTO.setIdProduto(rsCarrinho.getLong("id_produto"));
				carrinhoDTO.setQtdProduto(rsCarrinho.getInt("qtd_produto"));

				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE carrinho SET pago = true, num_pedido = ? , dt_compra = ?"
								+ " WHERE id_carrinho = ?");

				ps.setLong(1, proximoPedido);
				ps.setString(2, stringFormatadaDtCompra);
				ps.setLong(3, carrinhoDTO.getIdCarrinho());

				ps.execute();

				ps = (PreparedStatement) conn
						.prepareStatement("UPDATE produto SET qtd_estoque = (qtd_estoque - ?) "
								+ " WHERE id_produto = ?");
				ps.setInt(1, carrinhoDTO.getQtdProduto());
				ps.setLong(2, carrinhoDTO.getIdProduto());
				
				ps.execute();
			}
		
			return proximoPedido;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double getTotalCompra(long idCliente) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT SUM(valor * qtd_produto)"
						+ " FROM produto LEFT OUTER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND id_cliente = ? AND pago = false");
		ps.setLong(1, idCliente);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getDouble(1);
	}

	public double getTotalCompraPorNumPedido(long numPedido) {
		Connection conn = Conexao.getConexao();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT SUM(valor * qtd_produto)"
							+ " FROM produto LEFT OUTER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND num_pedido = ? AND pago = true");
			ps.setLong(1, numPedido);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public long getUltimoPedido(){
		Connection conn = Conexao.getConexao();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT MAX(num_pedido) FROM carrinho");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
}
