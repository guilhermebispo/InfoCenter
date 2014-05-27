package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infoCenter.apresentacao.ProdutoServlet;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.negocio.ProdutoBO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class ProdutoServlet extends HttpServlet {

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
		ProdutoDTO produtoDTO;
		ProdutoBO produtoBO = new ProdutoBO(new ProdutoDAO());
		
		try{
			
			if (acao.equals("cadastrar")) {
				
				produtoDTO = buscarProdutoDTOporRequest(req);
				produtoBO.cadastrarProduto(produtoDTO);
				resp.sendRedirect("sucesso.jsp");
	
			} else if (acao.equals("listar")) {
				
				req.setAttribute("produtos", produtoBO.buscarProdutos());
				req.getRequestDispatcher("_produto/produto_listar.jsp").forward(req, resp);
	
			} else if (acao.equals("excluir")) {
				
				produtoDTO = buscarProdutoDTOporRequest(req);
				produtoBO.excluirProduto(produtoDTO);
				resp.sendRedirect("sucesso.jsp");
	
			} else if (acao.equals("alterar")) {
				
				produtoDTO = buscarProdutoDTOporRequest(req);
				req.setAttribute("produto", produtoBO.buscarProdutoPorId(produtoDTO));
				req.getRequestDispatcher("_produto/produto_alterar.jsp").forward(req, resp);
	
			} else if (acao.equals("concluirAlteracao")) {
				
				produtoDTO = buscarProdutoDTOporRequest(req);
				produtoBO.alterarProduto(produtoDTO);
				resp.sendRedirect("sucesso.jsp");

			} else if (acao.equals("consultar")) {
				
				produtoDTO = buscarProdutoDTOporRequest(req);
				req.setAttribute("produtos", produtoBO.buscarProdutosPorDescMarca(produtoDTO));
				req.getRequestDispatcher("_produto/produto_listar.jsp").forward(req, resp);
			}
			
		} catch (SQLException sqlException){
			resp.sendRedirect("erro.jsp");
		}
	}
	
	private ProdutoDTO buscarProdutoDTOporRequest(HttpServletRequest req){
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if (req.getParameter("codBarra") != null){
			produtoDTO.setCodBarra(Integer.parseInt(req.getParameter("codBarra")));
		}
		produtoDTO.setDescricao(req.getParameter("descricao"));
		produtoDTO.setDtFabricacao(req.getParameter("dtFabricacao"));
		produtoDTO.setMarca(req.getParameter("marca"));
		produtoDTO.setObs(req.getParameter("observacao"));
		if (req.getParameter("qtdEstoque") != null){
			produtoDTO.setQtdEstoque(Integer.parseInt(req.getParameter("qtdEstoque")));
		}
		if (req.getParameter("valor") != null){
			double valor = Double.parseDouble(req.getParameter("valor").replace(".", "").replace(",", ""))/100;
			produtoDTO.setValorProduto(valor);
		}
		if (req.getParameter("idProduto") != null){
			produtoDTO.setIdProduto(Integer.parseInt(req.getParameter("idProduto")));
		}
		return produtoDTO;
	}
}
