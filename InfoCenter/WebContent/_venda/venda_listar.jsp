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
		
		<div class="l-content">
			<div class="pricing-tables pure-g">
				<% for (ProdutoDTO produto : produtos) { 
					out.println("<div class='pure-u-1 pure-u-med-1-3'>");
						out.println("<div class='pricing-table pricing-table-biz'>");
							out.println("<div class='pricing-table-header'>");
								out.println("<h2>" + produto.getDescricao() + "</h2>");
								out.println("<span class='pricing-table-price'> R$ " + String.format("%.2f", produto.getValorProduto()) + "<span>" + produto.getMarca() + "</span></span>");
							out.println("</div>");
		
							out.println("<ul class='pricing-table-list'>");
								out.println("<li>Fabricação: " + produto.getDtFabricacao() + "</li>");
								out.println("<li>Quantidade: " + produto.getQtdItensCarrinho() + "</li>");
							out.println("</ul>");
		
							out.println("<a class='button-choose pure-button' href='/InfoCenter/venda?acao=incluirCarrinho&idProduto=" + produto.getIdProduto() + "'>Adicionar no Carrinho</a>");
						out.println("</div>");
					out.println("</div>");
				 	}	%>
	
			</div>
		</div>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>