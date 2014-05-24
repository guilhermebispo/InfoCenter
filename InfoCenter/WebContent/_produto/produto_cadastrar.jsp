<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/tabela.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Cadastro de Produto</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.maskedinput-1.3.1.min_.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.price_format.2.0.min.js"></script>
</head>
<script type="text/javascript">
jQuery(function($) {
    $.mask.definitions['~']='[+-]';
    $('#dtFabricacao').mask('99/99/9999');
    $('#valor').priceFormat({
	    prefix: '',
	    centsSeparator: ',',
	    thousandsSeparator: '.'
	});
	$('#qtdEstoque').priceFormat({
		prefix : '',
		thousandsSeparator : '',
		limit : 5,
		centsLimit : 0
	});
});
</script>
<script type="text/javascript">   
$(document).ready( function() {
    $("#formCadastroProduto").validate({
        // Define as regras
        rules:{
        	codBarra:{
        		required: true
        	},
        	descricao:{
                required: true
            },
            dtFabricacao:{
                required: true
            },
            marca:{
                required: true
            },
            qtdEstoque:{
            	required: true
            },
            valor:{
            	required: true
            }
        },
        // Define as mensagens de erro para cada regra
        messages:{
        	codBarra:{
        		required: "O campo CÓDIGO DE BARRA é obrigatório!"
        	},
        	descricao:{
                required: "O campo DESCRIÇÃO é obrigatório!"
            },
            dtFabricacao:{
                required: "O campo DATA FABRICAÇÃO é obrigatório!"
            },
            marca:{
                required: "O campo MARCA é obrigatório!"
            },
            qtdEstoque:{
            	required: "O campo QTD ESTOQUE é obrigatório!"
            },
            valor:{
            	required: "O campo VALOR é obrigatório!"
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
	<form id="formCadastroProduto" name="formCadastroProduto" action="/InfoCenter/produto" method="post">
		<input type="hidden" value="cadastrar" name="acao"></input> 
		Código de Barra*: <input type="text" id="codBarra" name="codBarra"></input><br /> 
		Descrição*: <input type="text" id="descricao" name="descricao"></input><br /> 
		Fabricação*: <input type="text" id="dtFabricacao" name="dtFabricacao"></input><br />
		Marca*: <input type="text" id="marca" name="marca"></input><br /> 
		Observação: <input type="text" id="observacao" name="observacao"></input><br /> 
		Qtd Estoque*: <input type="text" id="qtdEstoque" name="qtdEstoque"></input><br /> 
		Valor*: <input type="text" id="valor" name="valor"></input><br /> 
		<input type="submit" value="Cadastrar"></input>
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>