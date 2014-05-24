<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>

<%!List<ProdutoDTO> produtos;%>
<%
	produtos = (List<ProdutoDTO>) request.getAttribute("produtosCarrinho");
%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Carrinho de Compras</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<form action="/InfoCenter/pagamento" method="post">
		<input type="hidden" value="finalizarCompra" name="acao"></input> 
		<table width="95%" border=1>
			<tr>
				<th>Código</th>
				<th>Descrição</th>
				<th>Marca</th>
				<th>Fabricação</th>
				<th>Qtd</th>
				<th>Valor</th>
				<th>SubTotal</th>
				<th>Ação</th>
			</tr>
			<%
				for (ProdutoDTO produto : produtos) {
					out.print("<tr>");
					out.print("<td>" + produto.getCodBarra() + "</td>");
					out.print("<td>" + produto.getDescricao() + "</td>");
					out.print("<td>" + produto.getMarca() + "</td>");
					out.print("<td>" + produto.getDtFabricacao() + "</td>");
					out.print("<td>" + produto.getQtdItensCarrinho() + "</td>");
					out.print("<td>R$ " + String.format("%.2f", produto.getValorProduto()) + "</td>");
					out.print("<td>R$ " + String.format("%.2f", produto.getValorTotalProduto()) + "</td>");
					out.print("<td><a href=/InfoCenter/venda?acao=apagarCarrinho&idProduto="
							+ produto.getIdProduto() + ">Apagar do Carrinho </a>");
					out.print("</tr>");
				}
			%>
			<tr>
				<td colspan="6">TOTAL DA COMPRA</td>
				<td colspan="6">R$ <%=request.getAttribute("valorTotalCompra") %></td>
			</tr> 
		</table>
		<input type="submit" value="Finalizar Compra"></input>
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>