package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.infoCenter.infra.ItemCarrinhoDTO;
import br.com.infoCenter.infra.ProdutoDTO;

public class CarrinhoDAO {

	public void incluirItemNoCarrinho(ItemCarrinhoDTO itemCarrinhoDTO) throws SQLException {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement ps;
		int qtd_produto = getQtdProduto(itemCarrinhoDTO);
		
		if (qtd_produto == 0) {
			ps = (PreparedStatement) conn.prepareStatement("INSERT INTO carrinho (id_cliente, id_produto, dt_compra, qtd_produto, pago) "
					+ "VALUES (?, ?, ?, ?, ?)");
			ps.setLong(1, itemCarrinhoDTO.getIdCliente());
			ps.setLong(2, itemCarrinhoDTO.getIdProduto());
			ps.setString(3, itemCarrinhoDTO.getDtCompra());
			ps.setLong(4, itemCarrinhoDTO.getQtdProduto());
			ps.setBoolean(5, itemCarrinhoDTO.isPago());
		} else {
			ps = (PreparedStatement) conn.prepareStatement("UPDATE carrinho SET dt_compra = ?, qtd_produto = ? "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setString(1, itemCarrinhoDTO.getDtCompra());
			ps.setLong(2, itemCarrinhoDTO.getQtdProduto() + qtd_produto);
			ps.setLong(3, itemCarrinhoDTO.getIdCliente());
			ps.setLong(4, itemCarrinhoDTO.getIdProduto());
			ps.setBoolean(5, itemCarrinhoDTO.isPago());
		}

		ps.execute();
	}

	public int getQtdProduto(ItemCarrinhoDTO itemCarrinhoDTO) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement ps;
		ps = (PreparedStatement) conn.prepareStatement
				("SELECT sum(qtd_produto) FROM carrinho WHERE id_produto = ? AND id_cliente = ? AND pago = ?");
		ps.setLong(1, itemCarrinhoDTO.getIdProduto());
		ps.setLong(2, itemCarrinhoDTO.getIdCliente());
		ps.setBoolean(3, itemCarrinhoDTO.isPago());
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

	public List<ProdutoDTO> getProdutosPagosPorNumPedido(long numPedido) throws SQLException {
		Connection conn = Conexao.getConexao();
		List<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
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
	}

	public void apagarItemDoCarrinho(ItemCarrinhoDTO itemCarrinhoDTO) throws SQLException {
		Connection conn = Conexao.getConexao();
		int qtd_produto = getQtdProduto(itemCarrinhoDTO);
		PreparedStatement ps;
		
		if (qtd_produto > 1){
			ps = (PreparedStatement) conn.prepareStatement("UPDATE carrinho SET dt_compra = ?, qtd_produto = ? "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setString(1, itemCarrinhoDTO.getDtCompra());
			ps.setLong(2, qtd_produto - itemCarrinhoDTO.getQtdProduto());
			ps.setLong(3, itemCarrinhoDTO.getIdCliente());
			ps.setLong(4, itemCarrinhoDTO.getIdProduto());
			ps.setBoolean(5, itemCarrinhoDTO.isPago());
			
		} else {
			ps = (PreparedStatement) conn.prepareStatement("DELETE FROM carrinho "
					+ "WHERE id_cliente = ? AND id_produto = ? AND pago = ?");
			ps.setLong(1, itemCarrinhoDTO.getIdCliente());
			ps.setLong(2, itemCarrinhoDTO.getIdProduto());
			ps.setBoolean(3, itemCarrinhoDTO.isPago());
		}
		
		ps.execute();
	}
	public long efetuarCompra(long idCliente, String stringFormatadaDtCompra) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement psCarrinho = (PreparedStatement) conn
				.prepareStatement("SELECT id_carrinho, id_cliente, id_produto, qtd_produto FROM carrinho WHERE id_cliente = ? AND pago = false");
		psCarrinho.setLong(1, idCliente);
		ResultSet rsCarrinho = psCarrinho.executeQuery();

		long proximoPedido = getUltimoPedido() + 1;
		while (rsCarrinho.next()) {
			ItemCarrinhoDTO itemCarrinhoDTO = new ItemCarrinhoDTO();
			itemCarrinhoDTO.setIdCarrinho(rsCarrinho.getLong("id_carrinho"));
			itemCarrinhoDTO.setIdCliente(rsCarrinho.getLong("id_cliente"));
			itemCarrinhoDTO.setIdProduto(rsCarrinho.getLong("id_produto"));
			itemCarrinhoDTO.setQtdProduto(rsCarrinho.getInt("qtd_produto"));

			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("UPDATE carrinho SET pago = true, num_pedido = ? , dt_compra = ?"
							+ " WHERE id_carrinho = ?");

			ps.setLong(1, proximoPedido);
			ps.setString(2, stringFormatadaDtCompra);
			ps.setLong(3, itemCarrinhoDTO.getIdCarrinho());

			ps.execute();

			ps = (PreparedStatement) conn
					.prepareStatement("UPDATE produto SET qtd_estoque = (qtd_estoque - ?) "
							+ " WHERE id_produto = ?");
			ps.setInt(1, itemCarrinhoDTO.getQtdProduto());
			ps.setLong(2, itemCarrinhoDTO.getIdProduto());
			
			ps.execute();
		}
	
		return proximoPedido;
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

	public double getTotalCompraPorNumPedido(long numPedido) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("SELECT SUM(valor * qtd_produto)"
						+ " FROM produto LEFT OUTER JOIN carrinho ON produto.id_produto = carrinho.id_produto AND num_pedido = ? AND pago = true");
		ps.setLong(1, numPedido);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getDouble(1);
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
