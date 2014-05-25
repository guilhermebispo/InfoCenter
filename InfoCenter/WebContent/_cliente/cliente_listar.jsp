<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="br.com.infoCenter.infra.ClienteDTO"%>

<%!List<ClienteDTO> clientes;%>
<%
	clientes = (List<ClienteDTO>) request.getAttribute("clientes");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Lista de Clientes</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Lista de Clientes</h3>
	
		<table width="95%" class="pure-table">
			<thead>
				<tr align="center">
					<th>Id</th>
					<th>Nome</th>
					<th>Cpf</th>
					<th>Login</th>
					<th>E-mail</th>
					<th>Data Nascimento</th>
					<th>Ação</th>
				</tr>
			</thead>
			<%
				int alternarLinha = 1;
				for (ClienteDTO cliente : clientes) {
					if (alternarLinha == 1){
						out.print("<tr>");
					} else {
						out.print("<tr class='pure-table-odd'>");
					}
					alternarLinha = alternarLinha * (-1);
					out.print("<td>" + cliente.getIdCliente() + "</td>");
					out.print("<td>" + cliente.getNome() + "</td>");
					out.print("<td align='center'>" + cliente.getCpf() + "</td>");
					out.print("<td>" + cliente.getLogin() + "</td>");
					out.print("<td>" + cliente.getEmail() + "</td>");
					out.print("<td align='center'>" + cliente.getDtNascimento() + "</td>");
					out.print("<td><a href=/InfoCenter/cliente?acao=excluir&idCliente="
							+ cliente.getIdCliente() + ">Excluir </a>");
					out.print("<a href=/InfoCenter/cliente?acao=alterar&idCliente="
							+ cliente.getIdCliente() + ">Alterar</a></td>");
					out.print("</tr>");
				}
			%>
		</table>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>