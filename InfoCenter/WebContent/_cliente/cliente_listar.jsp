<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ClienteDTO"%>

<%!List<ClienteDTO> clientes;%>
<%
	clientes = (List<ClienteDTO>) request.getAttribute("clientes");
%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Lista de Clientes</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<table width="95%" border=1>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Cpf</th>
			<th>Login</th>
			<th>Email</th>
			<th>Data Nascimento</th>
			<th>Ação</th>
		</tr>
		<%
			for (ClienteDTO cliente : clientes) {
				out.print("<tr>");
				out.print("<td>" + cliente.getIdCliente() + "</td>");
				out.print("<td>" + cliente.getNome() + "</td>");
				out.print("<td>" + cliente.getCpf() + "</td>");
				out.print("<td>" + cliente.getLogin() + "</td>");
				out.print("<td>" + cliente.getEmail() + "</td>");
				out.print("<td>" + cliente.getDtNascimento() + "</td>");
				out.print("<td><a href=/InfoCenter/cliente?acao=excluir&idCliente="
						+ cliente.getIdCliente() + ">Excluir </a>");
				out.print("<a href=/InfoCenter/cliente?acao=alterar&idCliente="
						+ cliente.getIdCliente() + ">Alterar</a></td>");
				out.print("</tr>");
			}
		%>
	</table>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>