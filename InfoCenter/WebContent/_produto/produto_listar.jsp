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
	<title>[InfoCenter] Lista de Produtos</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<table width="95%" border=1>
		<tr>
			<th>Id</th>
			<th>Código de Barra</th>
			<th>Descrição</th>
			<th>Data Fabricação</th>
			<th>Marca</th>
			<th>Observação</th>
			<th>Valor Unitário</th>
			<th>Ação</th>
		</tr>
		<%
			for (ProdutoDTO produto : produtos) {
				out.print("<tr>");
				out.print("<td>" + produto.getIdProduto() + "</td>");
				out.print("<td>" + produto.getCodBarra() + "</td>");
				out.print("<td>" + produto.getDescricao() + "</td>");
				out.print("<td>" + produto.getDtFabricacao() + "</td>");
				out.print("<td>" + produto.getMarca() + "</td>");
				out.print("<td>" + produto.getObs() + "</td>");
				out.print("<td>R$ " + Double.toString(produto.getValorProduto()) + "</td>");
				out.print("<td><a href=/InfoCenter/produto?acao=excluir&idProduto="
						+ produto.getIdProduto() + ">Excluir </a>");
				out.print("<a href=/InfoCenter/produto?acao=alterar&idProduto="
						+ produto.getIdProduto() + ">Alterar</a></td>");
				out.print("</tr>");
			}
		%>
	</table>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>