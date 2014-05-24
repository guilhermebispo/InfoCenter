package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.infoCenter.infra.NotaFiscalDTO;

public class NotaFiscalDAO {

	public List<NotaFiscalDTO> getNotas() {
		Connection conn = Conexao.getConexao();
		List<NotaFiscalDTO> notaFiscalDTOs = new ArrayList<NotaFiscalDTO>();
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("SELECT cliente.id_cliente id_cliente, nome, dt_compra, num_pedido, SUM(produto.valor * carrinho.qtd_produto) total FROM carrinho "
							+ "INNER JOIN cliente ON carrinho.id_cliente = cliente.id_cliente "
							+ "INNER JOIN produto ON carrinho.id_produto = produto.id_produto "
							+ "WHERE pago = TRUE "
							+ "GROUP BY num_pedido, cliente.id_cliente, nome, dt_compra "
							+ "ORDER BY num_pedido");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NotaFiscalDTO notaFiscalDTO = new NotaFiscalDTO();

				notaFiscalDTO.setIdCliente(rs.getLong("id_cliente"));
				notaFiscalDTO.setNomeCliente(rs.getString("nome"));
				notaFiscalDTO.setDtCompra(rs.getString("dt_compra"));
				notaFiscalDTO.setNumPedido(rs.getLong("num_pedido"));
				notaFiscalDTO.setTotal(rs.getDouble("total"));
				notaFiscalDTOs.add(notaFiscalDTO);
			}
			return notaFiscalDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}