<!DOCTYPE html>
<%@ page import="br.com.infoCenter.infra.ClienteDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Menu</title>
</head>
<body>
	<%
		ClienteDTO usuarioLogado = (ClienteDTO) session
				.getAttribute("usuarioLogado");
	%>
	<div id="menu">
		<table border=0 width="95%">
			<tr>
				<td>
					<h5>MENU: 
					<a href="/InfoCenter/login.jsp">Login</a> / 
					<a href="/InfoCenter/venda?acao=listar">Produtos</a> / 
					<a href="/InfoCenter/venda?acao=listarCarrinho">Carrinho de Compras</a>  
					<% 
					if (usuarioLogado != null && usuarioLogado.isAdministrador()) {
						out.print(" / <a href='/InfoCenter/_cliente/cliente_cadastrar.jsp'>Cadastrar Cliente</a> / ");
						out.print("<a href='/InfoCenter/_cliente/cliente_consultar.jsp'>Consultar Cliente</a> / ");
						out.print("<a href='/InfoCenter/cliente?acao=listar'>Listar Cliente</a> / ");
						out.print("<a href='/InfoCenter/_produto/produto_cadastrar.jsp'>Cadastrar Produto</a> / "); 
						out.print("<a href='/InfoCenter/_produto/produto_consultar.jsp'>Consultar Produto</a> / "); 
						out.print("<a href='/InfoCenter/produto?acao=listar'>Listar Produto</a> / ");
						out.print("<a href='/InfoCenter/pagamento?acao=emitirNota'>Emitir Nota</a> ");
					}
					%>
					</h5>
				</td>
				<td align="right">
					<h5>
						<%
							if (usuarioLogado != null) {
								out.print("Olá " + usuarioLogado.getNome() + "! <a href='/InfoCenter/login?acao=sair'>(Sair)</a>");
							}
						%>
					</h5>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>