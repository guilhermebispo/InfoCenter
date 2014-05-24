<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>[InfoCenter] Página de Login do PagSeguro</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<form action="/InfoCenter/pagamento" method="post">
		<input type="hidden" value="efetuarPagamento" name="acao"></input> 
		<h5>Obs.: Até o momento só aceitamos pagamento com o PagSeguro. Favor entre com o login do PagSeguro:</h5>
		<label for="usuario">Usuário: </label><input name="usuario" type="text" required="required"></input><br />
		<label for="senha">Senha: </label><input name="senha" type="password" required="required"></input><br />
		<b><br />TOTAL DA COMPRA: R$ <%=request.getAttribute("valorTotalCompra") %><br /><br /></b>
		<input type="submit" value="Confirmar Pagamento"></input>
		<input type="button" value="Cancelar" onClick="parent.location='venda?acao=listarCarrinho'"></input> 
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>