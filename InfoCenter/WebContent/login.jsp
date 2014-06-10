<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Página de Login</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
</head>
<script>
$(document).ready(function() {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Campo obrigatório!");
            }
        };
        elements[i].oninput = function(e) {
            e.target.setCustomValidity("");
        };
    }
});
</script>
<body>
	<jsp:include page="_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
	<h3 align="center" class="content-subhead">Login</h3>
	<form id="formLogar" class="pure-form" action="/InfoCenter/login" method="post">
		<fieldset class="pure-group">
			<input type="hidden" value="logar" name="acao"></input> 
			<input name="usuario" type="text" class="pure-input-1-4" placeholder="Usuário" required></input>
			<input name="senha" type="password" class="pure-input-1-4" placeholder="Senha" required></input>
		</fieldset>
		<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Logar</button>
		<h6><a href="_cliente/cliente_cadastrar.jsp">Caso não tenha usuário, cadastre-se!</a></h6>
	</form>
	</div>
	<jsp:include page="_template/template_rodape.jsp" />
</body>
</html>