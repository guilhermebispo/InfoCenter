<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Consulta de Cliente</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<form action="/InfoCenter/cliente" method="post">
		<input type="hidden" value="consultar" name="acao"></input> 
		Cpf: <input type="text" name="cpf"></input><br /> 
		Nome: <input type="text" name="nome"></input><br /> 
		<input type="submit" value="Consultar"></input><br />
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>