<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>
<%!List<ProdutoDTO> produtos;%>
<%
	produtos = (List<ProdutoDTO>) request.getAttribute("produtos");
%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Produtos a venda</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<table width="95%" border=1>
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Marca</th>
			<th>Fabricação</th>
			<th>Valor Unitário</th>
			<th>Ação</th>
			<th>Carrinho</th>
		</tr>
		<%
			for (ProdutoDTO produto : produtos) {
				out.print("<tr>");
				out.print("<td>" + produto.getCodBarra() + "</td>");
				out.print("<td>" + produto.getDescricao() + "</td>");
				out.print("<td>" + produto.getMarca() + "</td>");
				out.print("<td>" + produto.getDtFabricacao() + "</td>");
				out.print("<td>R$ " + String.format("%.2f", produto.getValorProduto()) + "</td>");
				out.print("<td><a href=/InfoCenter/venda?acao=incluirCarrinho&idProduto="
						+ produto.getIdProduto() + ">Incluir Carrinho </a>");
				out.print("<td>" + produto.getQtdItensCarrinho() + "</td>");
				out.print("</tr>");
			}
		%>
	</table>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>