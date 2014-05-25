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
	<title>[InfoCenter] Produtos a venda</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Produtos a Venda</h3>
		
		<table width="95%" class="pure-table">
			<thead>
				<tr align="center">
					<th>Código</th>
					<th>Descrição</th>
					<th>Marca</th>
					<th>Fabricação</th>
					<th>Valor Unitário</th>
					<th>Ação</th>
					<th>Carrinho</th>
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
					out.print("<td align='center'>" + produto.getDtFabricacao() + "</td>");
					out.print("<td align='right'>R$ " + String.format("%.2f", produto.getValorProduto()) + "</td>");
					out.print("<td align='center'><a href=/InfoCenter/venda?acao=incluirCarrinho&idProduto="
							+ produto.getIdProduto() + ">Incluir Carrinho </a>");
					out.print("<td>" + produto.getQtdItensCarrinho() + "</td>");
					out.print("</tr>");
				}
			%>
		</table>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>