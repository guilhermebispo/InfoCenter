<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.NotaFiscalDTO"%>

<%!List<NotaFiscalDTO> notasFiscais;%>
<%
	notasFiscais = (List<NotaFiscalDTO>) request.getAttribute("notasFiscais");
%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Notas Fiscais</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<table width="90%" border=1>
		<tr>
			<th>Número do Pedido</th>
			<th>Cliente</th>
			<th>Data da Compra</th>
			<th>Total</th>
			<th>Ação</th>
		</tr>
		<%
			for (NotaFiscalDTO notaFiscal : notasFiscais) {
				out.print("<tr>");
				out.print("<td  align='center'>" + notaFiscal.getNumPedido() + "</td>");
				out.print("<td  align='center'>" + notaFiscal.getNomeCliente() + "</td>");
				out.print("<td  align='center'>" + notaFiscal.getDtCompra() + "</td>");
				out.print("<td  align='right'> R$ " + String.format("%.2f", notaFiscal.getTotal()) + "</td>");
				out.print("<td  align='center'><a href=/InfoCenter/pagamento?acao=detalharNota&numPedido="
						+ notaFiscal.getNumPedido()
						+ "&idCliente="
						+ notaFiscal.getIdCliente()
						+ " target='_blank'>Emitir Nota </a>");
				out.print("</tr>");
			}
		%>
	</table>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>