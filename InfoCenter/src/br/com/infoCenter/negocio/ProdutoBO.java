package br.com.infoCenter.negocio;

import java.sql.SQLException;
import java.util.List;

import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class ProdutoBO {

	private ProdutoDAO produtoDAO;

	public ProdutoBO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	public void cadastrarProduto(ProdutoDTO produtoDTO) throws SQLException {
		produtoDAO.cadastrarProduto(produtoDTO);
	}

	public List<ProdutoDTO> buscarProdutos() throws SQLException {
		return produtoDAO.getProdutos();
	}

	public void excluirProduto(ProdutoDTO produtoDTO) throws SQLException {
		produtoDAO.excluirProduto(produtoDTO);
	}

	public ProdutoDTO buscarProdutoPorId(ProdutoDTO produtoDTO) throws SQLException {
		return produtoDAO.getProdutoPorId(produtoDTO.getIdProduto());
	}

	public void alterarProduto(ProdutoDTO produtoDTO) throws SQLException {
		produtoDAO.alterarProduto(produtoDTO);
	}

	public List<ProdutoDTO> buscarProdutosPorDescMarca(ProdutoDTO produtoDTO) throws SQLException {
		return produtoDAO.getProdutosPorDescMarca(produtoDTO);
	}

	public List<ProdutoDTO> buscarProdutosPorCliente(ClienteDTO usuarioLogado) throws SQLException {
		if(usuarioLogado == null){
			return produtoDAO.getProdutos();
		}
		return produtoDAO.getProdutosPorCliente(usuarioLogado.getIdCliente());
	}
	
}
