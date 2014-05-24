<!DOCTYPE html>
<%@ page import="br.com.infoCenter.infra.ClienteDTO"%>
<%!ClienteDTO cliente;%>
<%
	cliente = (ClienteDTO) request.getAttribute("cliente");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Alteração de Cliente</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.maskedinput-1.3.1.min_.js"></script>
</head>
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
    $("#formAlteracaoCliente").validate({
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
	<form id="formAlteracaoCliente" action="/InfoCenter/cliente" method="post">
		<input type="hidden" value="concluirAlteracao" name="acao"></input> 
		<input type="hidden" value="<%=cliente.getIdCliente()%>" name="idCliente"></input>
		Cpf*: <input type="text" id="cpf" name="cpf" value="<%=cliente.getCpf()%>"></input><br />
		Nome*: <input type="text" id="nome" name="nome" value="<%=cliente.getNome()%>"></input><br />
		Email*: <input type="text" id="nome" name="email" value="<%=cliente.getEmail()%>"></input><br /> 
		Cep*: <input type="text" id="cep" name="cep" value="<%=cliente.getCep()%>"></input><br />
		Data de Nascimento: <input type="text" id="data" name="dtNascimento" value="<%=cliente.getDtNascimento()%>"></input><br /> 
		Telefone*: <input type="text" id="telefone" name="telefone" value="<%=cliente.getTelefone()%>"></input><br />
		Endereço*: <input type="text" id="endereco" name="endereco" value="<%=cliente.getEndereco()%>"></input><br />
		Login*: <input type="text" id="login" name="login" value="<%=cliente.getLogin()%>"></input><br />
		Senha*: <input type="password" id="senha" name="senha" value="<%=cliente.getSenha()%>"></input><br />
		Confirmar Senha*: <input type="password" id="confirmarSenha" name="confirmarSenha"></input><br />
		<input type="submit" id="botaoAlterar" value="Alterar"></input>
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>