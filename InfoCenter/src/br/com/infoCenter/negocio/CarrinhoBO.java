package br.com.infoCenter.negocio;

import java.sql.SQLException;
import java.util.List;

import br.com.infoCenter.excecao.CarrinhoException;
import br.com.infoCenter.infra.CarrinhoDTO;
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

	public void incluirCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException, CarrinhoException {
		
		int qtdProduto = carrinhoDAO.getQtdProduto(carrinhoDTO);
		
		if (qtdProduto >= LIMITE_COMPRA) {
			throw new CarrinhoException("O limite de compra é " + LIMITE_COMPRA + " quantidade(s) por produto");
		}
			
		int qtdEstoque = (produtoDAO.getProdutoPorId(carrinhoDTO
				.getIdProduto())).getQtdEstoque();

		if (qtdProduto >= qtdEstoque) {
			throw new CarrinhoException("No momento temos " + qtdEstoque + " unidade(s) no estoque!");
		}
			
		carrinhoDAO.incluirCarrinho(carrinhoDTO);
		
	}

	public List<ProdutoDTO> listarCarrinho(ClienteDTO usuarioLogado) throws SQLException {
		return carrinhoDAO.getItensCarrinho(usuarioLogado.getIdCliente());
	}
	
	public void apagarCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException {
		carrinhoDAO.apagarCarrinho(carrinhoDTO);
	}
	
	public double buscarTotalCompra(ClienteDTO usuariologado) throws SQLException{
		return carrinhoDAO.getTotalCompra(usuariologado.getIdCliente());
	}
}
