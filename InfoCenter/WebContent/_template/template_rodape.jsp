<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Rodapé</title>
</head>
<body>
<div id="mensagemErro">
	<h5>
	<%
		String msg_erro = (String) request.getAttribute("msg_erro"); 
		if (msg_erro != null && !msg_erro.isEmpty()){
			out.print(msg_erro);
		}
	%>
	</h5>
</div>
<div id="rodape" class="footer l-box">
    <p>
        Copyright 2014. Todos os Direitos reservados a InfoCenter.
    </p>
</div>
</body>
</html>