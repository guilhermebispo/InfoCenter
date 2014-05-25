<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.NotaFiscalDTO"%>

<%!List<NotaFiscalDTO> notasFiscais;%>
<%
	notasFiscais = (List<NotaFiscalDTO>) request.getAttribute("notasFiscais");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Notas Fiscais</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Notas Fiscais</h3>
	
		<table width="90%" class="pure-table">
			<thead>
				<tr align="center">
					<th>Número do Pedido</th>
					<th>Cliente</th>
					<th>Data da Compra</th>
					<th>Total</th>
					<th>Ação</th>
				</tr>
			</thead>
			<%
				int alternarLinha = 1;
				for (NotaFiscalDTO notaFiscal : notasFiscais) {
					if (alternarLinha == 1){
						out.print("<tr>");
					} else {
						out.print("<tr class='pure-table-odd'>");
					}
					alternarLinha = alternarLinha * (-1);
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
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>