<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>

<%!List<ProdutoDTO> produtos;%>
<%
	produtos = (List<ProdutoDTO>) request.getAttribute("produtos");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Lista de Produtos</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Lista de Produtos</h3>

		<table width="95%" class="pure-table">
			<thead>
				<tr align="center">
					<th>Id</th>
					<th>Código de Barra</th>
					<th>Descrição</th>
					<th>Fabricação</th>
					<th>Marca</th>
					<th>Observação</th>
					<th>Valor Unitário</th>
					<th>Ação</th>
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
					out.print("<td>" + produto.getIdProduto() + "</td>");
					out.print("<td>" + produto.getCodBarra() + "</td>");
					out.print("<td>" + produto.getDescricao() + "</td>");
					out.print("<td align='center'>" + produto.getDtFabricacao() + "</td>");
					out.print("<td>" + produto.getMarca() + "</td>");
					out.print("<td>" + produto.getObs() + "</td>");
					out.print("<td align='right'>R$ " + Double.toString(produto.getValorProduto()) + "</td>");
					out.print("<td align='center'><a href=/InfoCenter/produto?acao=excluir&idProduto="
							+ produto.getIdProduto() + ">Excluir </a>");
					out.print("<a href=/InfoCenter/produto?acao=alterar&idProduto="
							+ produto.getIdProduto() + ">Alterar</a></td>");
					out.print("</tr>");
				}
			%>
		</table>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>