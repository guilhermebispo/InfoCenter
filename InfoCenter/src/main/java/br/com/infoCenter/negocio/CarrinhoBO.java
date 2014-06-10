package br.com.infoCenter.negocio;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.infoCenter.excecao.CarrinhoException;
import br.com.infoCenter.infra.ItemCarrinhoDTO;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.persistencia.CarrinhoDAO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class CarrinhoBO {

	private static final int LIMITE_COMPRA = 5;

	private CarrinhoDAO carrinhoDAO;
	private ProdutoDAO produtoDAO;

	public CarrinhoBO(CarrinhoDAO carrinhoDAO, ProdutoDAO produtoDAO) {
		this.carrinhoDAO = carrinhoDAO;
		this.produtoDAO = produtoDAO;
	}

	public void incluirItemNoCarrinho(ItemCarrinhoDTO itemCarrinhoDTO) throws SQLException, CarrinhoException {
		
		int qtdProduto = carrinhoDAO.getQtdProduto(itemCarrinhoDTO);
		
		if (qtdProduto >= LIMITE_COMPRA) {
			throw new CarrinhoException("O limite de compra é " + LIMITE_COMPRA + " quantidade(s) por produto");
		}
			
		int qtdEstoque = (produtoDAO.getProdutoPorId(itemCarrinhoDTO
				.getIdProduto())).getQtdEstoque();

		if (qtdProduto >= qtdEstoque) {
			throw new CarrinhoException("No momento temos " + qtdEstoque + " unidade(s) no estoque!");
		}
			
		carrinhoDAO.incluirItemNoCarrinho(itemCarrinhoDTO);
		
	}

	public List<ProdutoDTO> listarCarrinho(ClienteDTO usuarioLogado) throws SQLException {
		return carrinhoDAO.getItensCarrinho(usuarioLogado.getIdCliente());
	}
	
	public void apagarCarrinho(ItemCarrinhoDTO carrinhoDTO) throws SQLException {
		carrinhoDAO.apagarItemDoCarrinho(carrinhoDTO);
	}
	
	public double buscarTotalCompra(ClienteDTO usuarioLogado) throws SQLException{
		return carrinhoDAO.getTotalCompra(usuarioLogado.getIdCliente());
	}

	public long efetuarCompra(ClienteDTO usuarioLogado) throws SQLException {
		String stringFormatadaDtCompra = ((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
				.format(System.currentTimeMillis()));
		return carrinhoDAO.efetuarCompra(usuarioLogado.getIdCliente(), stringFormatadaDtCompra);
	}

	public List<ProdutoDTO> buscarProdutosPagosPorNumPedido(long numPedido) throws SQLException {
		return  carrinhoDAO.getProdutosPagosPorNumPedido(numPedido);
	}

	public double buscarTotalCompraPorNumPedido(long numPedido) throws SQLException {
		return carrinhoDAO.getTotalCompraPorNumPedido(numPedido);
	}
}
