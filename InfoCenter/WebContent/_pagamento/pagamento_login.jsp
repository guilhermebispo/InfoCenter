<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Login do PagSeguro</title>
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
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
	<h3 align="center" class="content-subhead">Login do PagSeguro</h3>
	<form class="pure-form" action="/InfoCenter/pagamento" method="post">
		<input type="hidden" value="efetuarPagamento" name="acao"></input> 
		<h5>Obs.: Até o momento só aceitamos pagamento com o PagSeguro. Favor entre com o login do PagSeguro:</h5>
		<fieldset class="pure-group">
			<input name="usuario" type="text" class="pure-input-1-4" placeholder="Usuário" required></input>
			<input name="senha" type="password" class="pure-input-1-4" placeholder="Senha" required></input>
		</fieldset>
		<b><br />TOTAL DA COMPRA: R$ <%=request.getAttribute("valorTotalCompra") %><br /><br /></b>
		<button type="submit" class="pure-button pure-input-1-8 pure-button-primary">Confirmar Pagamento</button>
		<input type="button" class="pure-button pure-input-1-8 pure-button-primary" value="Cancelar" onClick="parent.location='venda?acao=listarCarrinho'"></input>

	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>