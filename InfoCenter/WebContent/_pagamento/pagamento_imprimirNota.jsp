<!DOCTYPE html>
<%@page import="br.com.infoCenter.infra.ClienteDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>

<%!List<ProdutoDTO> produtos;%>
<%!ClienteDTO cliente;%>
<%
	produtos = (List<ProdutoDTO>) request.getAttribute("produtos");
	cliente = (ClienteDTO) request.getAttribute("cliente");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Detalhamento da Nota Fiscal</title>
	<link rel="stylesheet" href="/InfoCenter/css/pure.css">
	<link rel="stylesheet" href="/InfoCenter/css/main-grid.css">
	<link rel="stylesheet" href="/InfoCenter/css/layouts/pricing.css">
</head>
<body>
	<table width="100%" class="pure-table pure-table-bordered">
		<tr>
			<td width="200px"><img height="100%" width="100%" alt="InfoCenter" src="/InfoCenter/imagens/logomarca.png"></td>
			<td align="center"><h4>InfoCenter Ltda. CNPJ: 00.000.000/0001-00</h4>
				Site: www.infocenter.com.br / E-mail: infocenter@infocenter.com.br<br />
			</td>
		</tr>
	</table>
	<br />
	<table width="100%" class="pure-table pure-table-bordered">
		<tr>
			<td colspan="2" align="center"><b>NÚMERO DO PEDIDO: <%= request.getAttribute("numPedido") %></b></td>
		</tr>
		<tr>
			<td colspan="2"><b>Cliente: </b><%= cliente.getNome() %></td>
		</tr>
		<tr>
			<td colspan="2"><b>Endereço: </b><%= cliente.getEndereco() %></td>
		</tr>
		<tr>
			<td><b>CEP: </b><%= cliente.getCep() %></td>
			<td><b>Telefone: </b><%= cliente.getTelefone() %></td>
		</tr>
	</table>
	<br />
	<table width="100%" class="pure-table pure-table-bordered">
		<thead>
			<tr align="center">
				<th>Código</th>
				<th>Descrição</th>
				<th>Qtd</th>
				<th>Valor (R$)</th>
				<th>Total (R$)</th>
			</tr>
		</thead>
		<%
			for (ProdutoDTO produto : produtos) {
				out.print("<tr>");
				out.print("<td>" + produto.getCodBarra() + "</td>");
				out.print("<td>" + produto.getDescricao() + "</td>");
				out.print("<td align='center'>" + produto.getQtdItensCarrinho() + "</td>");
				out.print("<td align='right'>" + String.format("%.2f", produto.getValorProduto()) + "</td>");
				out.print("<td align='right'>" + String.format("%.2f", produto.getValorTotalProduto()) + "</td>");
				out.print("</tr>");
			}
		%>
		<tr>
			<td colspan="4" align="center"><b>TOTAL DA COMPRA (R$):</b></td>
			<td align="right"><b><%= request.getAttribute("totalCompra")%></b></td>
		</tr>
		
	</table>
	<br>
	<center>
		<input type="button" name="print" class="pure-button pure-input-1-4 pure-button-primary" value="Imprimir" onClick="javascript:window.print();"></input>
	</center>
	<style media="print">
		.botao {
			display: none;
		}
	</style>
</body>
</html>