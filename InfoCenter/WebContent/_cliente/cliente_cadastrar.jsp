<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Cadastro de Cliente</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.maskedinput-1.3.1.min_.js"></script>
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
})
</script>
<script type="text/javascript">
jQuery(function($) {
      $.mask.definitions['~']='[+-]';
      $('#data').mask('99/99/9999');
      $('#cpf').mask('999.999.999-99');
      $('#telefone').mask('(99) 9999-9999');
      $('#cep').mask('99.999-999');
   });
</script>
<script type="text/javascript">   
$(document).ready( function() {
    $("#formCadastroCliente").validate({
        // Define as regras
        rules:{
        	cpf:{
        		required: true
        	},
        	nome:{
                required: true
            },
            email:{
                required: true, email: true
            },
            cep:{
                required: true
            },
            telefone:{
            	required: true
            },
            endereco:{
            	required: true
            },
            login:{
            	required: true
            },
            senha:{
            	required: true, minlength: 6, maxlength: 6
            },
            confirmarSenha:{
            	required: true, minlength: 6, maxlength: 6, equalTo: "#senha"
            }
        },
        // Define as mensagens de erro para cada regra
        messages:{
        	cpf:{
        		required: "O campo CPF é obrigatório!"
        	},
        	nome:{
                required: "O campo NOME é obrigatório!"
            },
            email:{
                required: "O campo EMAIL é obrigatório!", email: "Digite um e-mail válido!"
            },
            cep:{
                required: "O campo CEP é obrigatório!"
            },
            telefone:{
            	required: "O campo TELEFONE é obrigatório!"
            },
            endereco:{
            	required: "O campo ENDEREÇO é obrigatório!"
            },
            login:{
            	required: "O campo LOGIN é obrigatório!"
            },
            senha:{
            	required: "O campo SENHA é obrigatório!", 
            	minlength: "O campo SENHA deve conter 6 caracteres!", 
            	maxlength: "O campo SENHA deve conter 6 caracteres!"
            },
            confirmarSenha:{
            	required: "O campo CONFIRMAR SENHA é obrigatório!", 
            	minlength: "O campo CONFIRMAR SENHA deve conter 6 caracteres!", 
            	maxlength: "O campo CONFIRMAR SENHA deve conter 6 caracteres!", 
            	equalTo: "A confirmação da senha não confere!"
            }
        }
    });
});
</script>
<style type="text/css">
    label { display: inline; padding-left: 10px; margin-top: 10px; font-family: Verdana; font-size: 96%;}
    label.error { float: none; color: red; margin: 0 .5em 0 0; vertical-align: middle; font-size: 10px }
</style>
<body>
	<jsp:include page="../_template/template_cabecalho.jsp" />
	<div class="1-content" align="center">
	<h3 align="center" class="content-subhead">Cadastro de Cliente</h3>
	<form class="pure-form pure-form-aligned" id="formCadastroCliente" action="/InfoCenter/cliente" method="post">
		<input type="hidden" value="cadastrar" name="acao"></input>
		<div class="pure-control-group">
			<label for="cpf">CPF(*)</label>
			<input type="text" id="cpf" name="cpf" class="pure-input-1-4" placeholder="CPF" required></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Nome(*)</label>
			<input type="text" id="nome" name="nome" class="pure-input-1-4" placeholder="Nome" required></input>
		</div>
		<div class="pure-control-group">
			<label for="nome">E-mail(*)</label>
			<input type="text" id="email" name="email" class="pure-input-1-4" placeholder="E-mail" required></input>
		</div>
		<div class="pure-control-group">
			<label for="nome">CEP(*)</label>
			<input type="text" id="cep" name="cep" class="pure-input-1-4" placeholder="CEP" required></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Data de Nascimento</label>
			<input type="text" id="data" name="dtNascimento" class="pure-input-1-4" placeholder="Data de Nascimento"></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Telefone(*)</label>
			<input type="text" id="telefone" name="telefone" class="pure-input-1-4" placeholder="Telefone" required></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Endereço(*)</label>
			<input type="text" id="endereco" name="endereco" class="pure-input-1-4" placeholder="Endereço" required></input>
		</div>
		<div class="pure-control-group">
			<label for="nome">Login(*)</label>
			<input type="text" id="login" name="login" class="pure-input-1-4" placeholder="Login" required></input>
		</div>
		<div class="pure-control-group">
			<label for="nome">Senha(*)</label>
			<input type="password" id="senha" name="senha" class="pure-input-1-4" placeholder="Senha" required></input> 
		</div>
		<div class="pure-control-group">
			<label for="nome">Confirmar Senha(*)</label>
			<input type="password" id="confirmarSenha" name="confirmarSenha" class="pure-input-1-4" placeholder="Confirmar senha" required></input>
		</div>
		<div class="pure-controls">
			<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Cadastrar</button>
		</div>
	</form>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>