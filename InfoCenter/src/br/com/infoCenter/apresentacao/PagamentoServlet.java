package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.negocio.CarrinhoBO;
import br.com.infoCenter.negocio.ClienteBO;
import br.com.infoCenter.negocio.LoginBO;
import br.com.infoCenter.negocio.NotaFiscalBO;
import br.com.infoCenter.persistencia.CarrinhoDAO;
import br.com.infoCenter.persistencia.ClienteDAO;
import br.com.infoCenter.persistencia.NotaFiscalDAO;
import br.com.infoCenter.persistencia.ProdutoDAO;

public class PagamentoServlet extends HttpServlet {

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
		
		ClienteDAO clienteDAO = new ClienteDAO();
		ClienteBO clienteBO = new ClienteBO(clienteDAO);
		LoginBO loginBO = new LoginBO(clienteDAO);
		CarrinhoBO carrinhoBO = new CarrinhoBO(new CarrinhoDAO(), new ProdutoDAO());
		
		ClienteDTO usuarioLogado = loginBO.buscarUsuarioLogado(req);
		NotaFiscalBO notaFiscalBO = new NotaFiscalBO(new NotaFiscalDAO());

		try{
			if (usuarioLogado == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
	
			} else if (acao.equals("finalizarCompra")) {
				double valorTotalCompra = carrinhoBO.buscarTotalCompra(usuarioLogado);
				req.setAttribute("valorTotalCompra", valorTotalCompra);
				req.getRequestDispatcher("_pagamento/pagamento_login.jsp").forward(req, resp);
				
			} else if (acao.equals("efetuarPagamento")) {
				long numeroPedido = carrinhoBO.efetuarCompra(usuarioLogado);
				req.setAttribute("numeroPedido", numeroPedido);
				req.getRequestDispatcher("_pagamento/pagamento_sucesso.jsp").forward(req,resp);
			
			} else if (acao.equals("emitirNota")) {
				req.setAttribute("notasFiscais", notaFiscalBO.buscarNotas());
				req.getRequestDispatcher("_pagamento/pagamento_nota.jsp").forward(req, resp);
			
			} else if (acao.equals("detalharNota")) {
				long numPedido = Long.parseLong(req.getParameter("numPedido"));
				long idCliente = Long.parseLong(req.getParameter("idCliente"));
				req.setAttribute("cliente", clienteBO.buscarClientePorId(idCliente));
				req.setAttribute("produtos", carrinhoBO.buscarProdutosPagosPorNumPedido(numPedido));
				req.setAttribute("totalCompra", carrinhoBO.buscarTotalCompraPorNumPedido(numPedido)); 
				req.setAttribute("numPedido", numPedido);
				req.getRequestDispatcher("_pagamento/pagamento_imprimirNota.jsp").forward(req, resp);
			}
		} catch (SQLException sqlException) {
			resp.sendRedirect("erro.jsp");
		}
	}
}