<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Página de Sucesso de Pagamento</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<h3>Compra efetuada com sucesso, o número do seu pedido é: <%=request.getAttribute("numeroPedido") %></h3>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>