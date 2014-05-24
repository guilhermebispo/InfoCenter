package br.com.infoCenter.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infoCenter.apresentacao.ProdutoServlet;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class ProdutoServlet extends HttpServlet {

	/**
	 * 
	 */
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
		ProdutoDTO produtoDTO = new ProdutoDTO();
		ProdutoDAO produtoDao = new ProdutoDAO();
		if (acao.equals("cadastrar")) {
			produtoDTO.setCodBarra(Integer.parseInt(req
					.getParameter("codBarra")));
			produtoDTO.setDescricao(req.getParameter("descricao"));
			produtoDTO.setDtFabricacao(req.getParameter("dtFabricacao"));
			produtoDTO.setMarca(req.getParameter("marca"));
			produtoDTO.setObs(req.getParameter("observacao"));
			produtoDTO.setQtdEstoque(Integer.parseInt(req
					.getParameter("qtdEstoque")));
			double valor = Double.parseDouble(req.getParameter("valor").replace(".", "").replace(",", ""))/100;
			produtoDTO.setValorProduto(valor);
			if (produtoDao.cadastrarProduto(produtoDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}

		} else if (acao.equals("listar")) {
			req.setAttribute("produtos", produtoDao.getProdutos());
			req.getRequestDispatcher("_produto/produto_listar.jsp").forward(req, resp);

		} else if (acao.equals("excluir")) {
			produtoDTO.setIdProduto(Long.parseLong(req
					.getParameter("idProduto")));
			if (produtoDao.excluirProduto(produtoDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}

		} else if (acao.equals("alterar")) {
			req.setAttribute("produto", produtoDao
					.getProdutoPorId(Integer.parseInt(req
							.getParameter("idProduto"))));
			req.getRequestDispatcher("_produto/produto_alterar.jsp").forward(req, resp);

		} else if (acao.equals("concluirAlteracao")) {
			
			produtoDTO.setCodBarra(Integer.parseInt(req
					.getParameter("codBarra")));
			produtoDTO.setDescricao(req.getParameter("descricao"));
			produtoDTO.setDtFabricacao(req.getParameter("dtFabricacao"));
			produtoDTO.setMarca(req.getParameter("marca"));
			produtoDTO.setObs(req.getParameter("obs"));
			produtoDTO.setQtdEstoque(Integer.parseInt(req
					.getParameter("qtdEstoque")));
			double valor = Double.parseDouble(req.getParameter("valor").replace(".", "").replace(",", ""))/100;
			produtoDTO.setValorProduto(valor);
			produtoDTO.setIdProduto(Integer.parseInt(req
					.getParameter("idProduto")));
			
			if (produtoDao.alterarProduto(produtoDTO)) {
				resp.sendRedirect("sucesso.jsp");
			} else {
				resp.sendRedirect("erro.jsp");
			}
		} else if (acao.equals("consultar")) {
			produtoDTO.setDescricao(req.getParameter("descricao"));
			produtoDTO.setMarca(req.getParameter("marca"));
			
			req.setAttribute("produtos", produtoDao.getProdutosPorDescMarca(produtoDTO));
			req.getRequestDispatcher("_produto/produto_listar.jsp").forward(req, resp);
		}
	}
}
