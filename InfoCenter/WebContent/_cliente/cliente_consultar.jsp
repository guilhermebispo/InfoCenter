<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Consulta de Cliente</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.maskedinput-1.3.1.min_.js"></script>
</head>
<script type="text/javascript">
jQuery(function($) {
      $.mask.definitions['~']='[+-]';
      $('#cpf').mask('999.999.999-99');
   });
</script>

<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
	<h3 align="center" class="content-subhead">Consulta de Cliente</h3>
	<form class="pure-form pure-form-aligned" id="formConsultaCliente" action="/InfoCenter/cliente" method="post">
		<input type="hidden" value="consultar" name="acao"></input>
		<div class="pure-control-group">
			<label for="cpf">CPF(*)</label>
			<input type="text" id="cpf" name="cpf" class="pure-input-1-4" placeholder="CPF"></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Nome(*)</label>
			<input type="text" id="nome" name="nome" class="pure-input-1-4" placeholder="Nome"></input>
		</div>
		<div class="pure-controls">
			<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Consultar</button>
		</div>
	</form>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>