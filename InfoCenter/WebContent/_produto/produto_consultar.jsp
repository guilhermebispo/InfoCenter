<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Consulta de Produto</title>
</head>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Consulta de Produto</h3>
		<form id="formConsultaProduto" class="pure-form pure-form-aligned" action="/InfoCenter/produto" method="post">
			<input type="hidden" value="consultar" name="acao"></input> 
			<div class="pure-control-group">
				<label for="descricao">Descrição</label>
				<input type="text" id="descricao" name="descricao" class="pure-input-1-4" placeholder="Descrição"></input> 
			</div>
			<div class="pure-control-group">
				<label for="marca">Marca</label>
				<input type="text" id="marca" name="marca" class="pure-input-1-4" placeholder="Marca"></input> 
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Consultar</button>
			</div>
		</form>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>