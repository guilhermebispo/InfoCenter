package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.infra.CarrinhoDTO;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.persistencia.CarrinhoDAO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class VendaServlet extends HttpServlet {

	private static final int LIMITE_COMPRA = 5;
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
		HttpSession sessao = req.getSession();
		String acao = req.getParameter("acao");
		ProdutoDAO produtoDao = new ProdutoDAO();
		CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
		ClienteDTO usuarioLogado = (ClienteDTO) sessao
				.getAttribute("usuarioLogado");
		CarrinhoDTO carrinhoDto = new CarrinhoDTO();

		try{
			
			if (acao.equals("listar")) {
				List<ProdutoDTO> produtos;
				if (usuarioLogado == null) {
					produtos = produtoDao.getProdutosPorCliente(-1);
				} else {
					produtos = produtoDao.getProdutosPorCliente(usuarioLogado
							.getIdCliente());
				}
				req.setAttribute("produtos", produtos);
				req.getRequestDispatcher("_venda/venda_listar.jsp").forward(req,
						resp);
	
			} else if (usuarioLogado == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
	
			} else if (acao.equals("incluirCarrinho")) {
				carrinhoDto.setIdProduto(Integer.parseInt(req
						.getParameter("idProduto")));
				carrinhoDto.setQtdProduto(1);
				carrinhoDto.setIdCliente(usuarioLogado.getIdCliente());
				carrinhoDto.setPago(false);
				int qtdProduto = carrinhoDAO.getQtdProduto(carrinhoDto);
	
				if (qtdProduto >= LIMITE_COMPRA) {
					req.setAttribute("msg_erro",
							"O limite de compra � de 5 quantidades por produto!");
					req.setAttribute("produtos", produtoDao
							.getProdutosPorCliente(usuarioLogado.getIdCliente()));
					req.getRequestDispatcher("_venda/venda_listar.jsp").forward(
							req, resp);
	
				} else {
					int qtdEstoque = (produtoDao.getProdutoPorId(carrinhoDto
							.getIdProduto())).getQtdEstoque();
					if (qtdProduto >= qtdEstoque) {
						req.setAttribute("msg_erro", "No momento temos "
								+ qtdEstoque + " unidade no estoque!");
						req.setAttribute("produtos",
								produtoDao.getProdutosPorCliente(usuarioLogado
										.getIdCliente()));
						req.getRequestDispatcher("_venda/venda_listar.jsp")
								.forward(req, resp);
	
					} else if (carrinhoDAO.incluirCarrinho(carrinhoDto)) {
						req.setAttribute("produtos",
								produtoDao.getProdutosPorCliente(usuarioLogado
										.getIdCliente()));
						req.getRequestDispatcher("_venda/venda_listar.jsp")
								.forward(req, resp);
					} else {
						resp.sendRedirect("erro.jsp");
					}
				}
	
			} else if (acao.equals("listarCarrinho")) {
				List<ProdutoDTO> produtos = carrinhoDAO.getProdutos(usuarioLogado
						.getIdCliente());
				req.setAttribute("produtosCarrinho", produtos);
				req.setAttribute("valorTotalCompra", getTotalCompra(produtos));
				req.getRequestDispatcher("_venda/carrinho_listar.jsp").forward(req,
						resp);
	
			} else if (acao.equals("apagarCarrinho")) {
				carrinhoDto.setIdProduto(Integer.parseInt(req
						.getParameter("idProduto")));
				carrinhoDto.setQtdProduto(1);
				carrinhoDto.setIdCliente(usuarioLogado.getIdCliente());
				carrinhoDto.setPago(false);
				carrinhoDto
						.setDtCompra((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
								.format(System.currentTimeMillis()));
				if (carrinhoDAO.apagarCarrinho(carrinhoDto)) {
					req.getRequestDispatcher("venda?acao=listarCarrinho").forward(
							req, resp);
				} else {
					resp.sendRedirect("erro.jsp");
				}
			}
		} catch (SQLException sqlException){
			resp.sendRedirect("erro.jsp");
		}
	}

	private double getTotalCompra(List<ProdutoDTO> produtos) {
		double valorTotalCompra = 0;
		for (ProdutoDTO produtoDTO : produtos) {
			valorTotalCompra += produtoDTO.getValorTotalProduto();
		}
		return valorTotalCompra;
	}
}