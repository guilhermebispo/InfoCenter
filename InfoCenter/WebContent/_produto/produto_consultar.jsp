<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Consulta de Produto</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<form action="/InfoCenter/produto" method="post">
		<input type="hidden" value="consultar" name="acao"></input> 
		Descrição: <input type="text" name="descricao"></input><br /> 
		Marca: <input type="text" name="marca"></input><br />
		<input type="submit" value="Consultar"></input><br />
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>