package br.com.infoCenter.apresentacao;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.infoCenter.infra.ClienteDTO;
import br.com.infoCenter.persistencia.CarrinhoDAO;
import br.com.infoCenter.persistencia.ClienteDAO;
import br.com.infoCenter.persistencia.NotaFiscalDAO;

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
		HttpSession sessao = req.getSession();
		String acao = req.getParameter("acao");
		
		CarrinhoDAO carrinhoDao = new CarrinhoDAO();
		NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		ClienteDTO usuarioLogado = (ClienteDTO) sessao
				.getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		} else if (acao.equals("finalizarCompra")) {
			double valorTotalCompra = carrinhoDao.getTotalCompra(usuarioLogado.getIdCliente());
			req.setAttribute("valorTotalCompra", valorTotalCompra);
			req.getRequestDispatcher("_pagamento/pagamento_login.jsp").forward(req, resp);
			
		} else if (acao.equals("efetuarPagamento")) {
			String stringFormatadaDtCompra = ((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
					.format(System.currentTimeMillis()));

			long numeroPedido = carrinhoDao.efetuarCompra(usuarioLogado.getIdCliente(), stringFormatadaDtCompra);
			req.setAttribute("numeroPedido", numeroPedido);
			req.getRequestDispatcher("_pagamento/pagamento_sucesso.jsp").forward(req,
					resp);
		
		} else if (acao.equals("emitirNota")) {
			req.setAttribute("notasFiscais", notaFiscalDAO.getNotas());
			req.getRequestDispatcher("_pagamento/pagamento_nota.jsp").forward(req, resp);
		
		} else if (acao.equals("detalharNota")) {
			long numPedido = Long.parseLong(req.getParameter("numPedido"));
			long idCliente = Long.parseLong(req.getParameter("idCliente"));
			req.setAttribute("cliente", clienteDAO.getClientePorId(idCliente));
			req.setAttribute("produtos", carrinhoDao.getProdutosPagosPorNumPedido(numPedido));
			req.setAttribute("totalCompra", carrinhoDao.getTotalCompraPorNumPedido(numPedido));
			req.setAttribute("numPedido", numPedido);
			req.getRequestDispatcher("_pagamento/pagamento_imprimirNota.jsp").forward(req, resp);
		}
	}
}