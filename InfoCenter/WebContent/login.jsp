<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>[InfoCenter] Página de Login</title>
</head>
<body>
	<jsp:include page="_template/template_cabecalho.jsp" />
	<form action="/InfoCenter/login" method="post">
		<input type="hidden" value="logar" name="acao"></input> 
		<label for="usuario">Usuário: </label><input name="usuario" type="text"></input><br />
		<label for="senha">Senha: </label><input name="senha" type="password"></input><br />
		<input type="submit" value="Logar"></input> <br />
		<h6><a href="_cliente/cliente_cadastrar.jsp">Caso não tenha usuário, cadastre-se!</a></h6>
	</form>
	<jsp:include page="_template/template_rodape.jsp" />
</body>
</html>