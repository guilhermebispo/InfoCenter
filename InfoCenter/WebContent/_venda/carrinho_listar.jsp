<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>

<%!List<ProdutoDTO> produtos;%>
<%
	produtos = (List<ProdutoDTO>) request.getAttribute("produtosCarrinho");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Carrinho de Compras</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Carrinho de Compra</h3>

		<form action="/InfoCenter/pagamento" method="post">
			<input type="hidden" value="finalizarCompra" name="acao"></input> 
			<table width="95%" class="pure-table">
				<thead>
					<tr align="center">
						<th>Código</th>
						<th>Descrição</th>
						<th>Marca</th>
						<th>Fabricação</th>
						<th>Qtd</th>
						<th>Valor</th>
						<th>Ação</th>
						<th>SubTotal</th>
					</tr>
				</thead>
				<%
					int alternarLinha = 1;
					for (ProdutoDTO produto : produtos) {
						if (alternarLinha == 1){
							out.print("<tr>");
						} else {
							out.print("<tr class='pure-table-odd'>");
						}
						alternarLinha = alternarLinha * (-1);
						out.print("<td>" + produto.getCodBarra() + "</td>");
						out.print("<td>" + produto.getDescricao() + "</td>");
						out.print("<td>" + produto.getMarca() + "</td>");
						out.print("<td>" + produto.getDtFabricacao() + "</td>");
						out.print("<td>" + produto.getQtdItensCarrinho() + "</td>");
						out.print("<td align='right'>R$ " + String.format("%.2f", produto.getValorProduto()) + "</td>");
						out.print("<td><a href=/InfoCenter/venda?acao=apagarCarrinho&idProduto="
								+ produto.getIdProduto() + ">Apagar do Carrinho </a>");
						out.print("<td align='right'>R$ " + String.format("%.2f", produto.getValorTotalProduto()) + "</td>");
						out.print("</tr>");
					}
					
					out.print("<tfoot>");
					if (alternarLinha == 1){
						out.print("<tr>");
					} else {
						out.print("<tr class='pure-table-odd'>");
					}
					out.print("<td colspan='7' align='center'><b>TOTAL DA COMPRA</b></td>");
					out.print("<td align='right'><b>R$ " + request.getAttribute("valorTotalCompra") + "</b></td>");
					out.print("</tr>");
					out.print("</tfoot>");
				%>
			</table>
			<br />
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Finalizar Compra</button>
			</div>
		</form>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>