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

import br.com.infoCenter.excecao.CarrinhoException;
import br.com.infoCenter.infra.CarrinhoDTO;
import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.infra.ProdutoDTO;
import br.com.infoCenter.negocio.CarrinhoBO;
import br.com.infoCenter.negocio.LoginBO;
import br.com.infoCenter.negocio.ProdutoBO;
import br.com.infoCenter.persistencia.CarrinhoDAO;
import br.com.infoCenter.persistencia.ClienteDAO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class VendaServlet extends HttpServlet {

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
		LoginBO loginBO = new LoginBO(new ClienteDAO());
		CarrinhoBO carrinhoBO = new CarrinhoBO(new CarrinhoDAO(), new ProdutoDAO());
		ProdutoBO produtoBO = new ProdutoBO(new ProdutoDAO());

		ClienteDTO usuarioLogado = loginBO.buscarUsuarioLogado(req);
		
		try{
			
			if (acao.equals("listar")) {
				List<ProdutoDTO> produtos = produtoBO.buscarProdutosPorCliente(usuarioLogado);
				req.setAttribute("produtos", produtos);
				req.getRequestDispatcher("_venda/venda_listar.jsp").forward(req,resp);
	
			} else if (usuarioLogado == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
	
			} else if (acao.equals("incluirCarrinho")) {
				CarrinhoDTO carrinhoDTO = buscarCarrinhoDTOPorRequest(req);
				try {
					carrinhoBO.incluirCarrinho(carrinhoDTO);
				} catch (CarrinhoException e) {
					req.setAttribute("msg_erro", e.getMessage());
				} finally{
					req.setAttribute("produtos", produtoBO.buscarProdutosPorCliente(usuarioLogado));
					req.getRequestDispatcher("_venda/venda_listar.jsp").forward(req, resp);
				}
	
			} else if (acao.equals("listarCarrinho")) {
				List<ProdutoDTO> produtos = carrinhoBO.listarCarrinho(usuarioLogado);
				req.setAttribute("produtosCarrinho", produtos);
				req.setAttribute("valorTotalCompra", carrinhoBO.buscarTotalCompra(usuarioLogado));
				req.getRequestDispatcher("_venda/carrinho_listar.jsp").forward(req,resp);
	
			} else if (acao.equals("apagarCarrinho")) {
				CarrinhoDTO carrinhoDTO = buscarCarrinhoDTOPorRequest(req);
				carrinhoBO.apagarCarrinho(carrinhoDTO);
				req.getRequestDispatcher("venda?acao=listarCarrinho").forward(req, resp);
			}
			
		} catch (SQLException sqlException){
			resp.sendRedirect("erro.jsp");
		}
	}

	private CarrinhoDTO buscarCarrinhoDTOPorRequest (HttpServletRequest req){
		ClienteDTO usuarioLogado = (ClienteDTO) req.getSession().getAttribute("usuarioLogado");
		CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
		carrinhoDTO.setIdProduto(Integer.parseInt(req.getParameter("idProduto")));
		carrinhoDTO.setQtdProduto(1);
		carrinhoDTO.setIdCliente(usuarioLogado.getIdCliente());
		carrinhoDTO.setPago(false);
		carrinhoDTO.setDtCompra((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
						.format(System.currentTimeMillis()));
		return carrinhoDTO;
	}

}