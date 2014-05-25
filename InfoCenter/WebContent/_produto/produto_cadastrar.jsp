<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Cadastro de Produto</title>
	<script type="text/javascript" src="/InfoCenter/js/jquery.min.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.maskedinput-1.3.1.min_.js"></script>
	<script type="text/javascript" src="/InfoCenter/js/jquery.price_format.2.0.min.js"></script>
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
	<div class="1-content" align="center">
		<h3 align="center" class="content-subhead">Cadastro de Produto</h3>
		<form id="formCadastroProduto" name="formCadastroProduto" class="pure-form pure-form-aligned" action="/InfoCenter/produto" method="post">
			<input type="hidden" value="cadastrar" name="acao"></input> 
			<div class="pure-control-group">
				<label for="codBarra">Código de Barra(*)</label>
				<input type="text" id="codBarra" name="codBarra" class="pure-input-1-4" placeholder="Código de Barra" required></input> 
			</div>
			<div class="pure-control-group">
				<label for="descricao">Descrição(*)</label>
				<input type="text" id="descricao" name="descricao" class="pure-input-1-4" placeholder="Descrição" required></input> 
			</div>
			<div class="pure-control-group">
				<label for="dtFabricacao">Fabricação(*)</label>
				<input type="text" id="dtFabricacao" name="dtFabricacao" class="pure-input-1-4" placeholder="Data de Fabricação" required></input>
			</div>
			<div class="pure-control-group">
				<label for="marca">Marca(*)</label>
				<input type="text" id="marca" name="marca" class="pure-input-1-4" placeholder="Marca" required></input> 
			</div>
			<div class="pure-control-group">
				<label for="observação">Observação</label>
				<input type="text" id="observacao" name="observacao" class="pure-input-1-4" placeholder="Observação"></input> 
			</div>
			<div class="pure-control-group">
				<label for="qtdEstoque">Qtd Estoque(*)</label>
				<input type="text" id="qtdEstoque" name="qtdEstoque" class="pure-input-1-4" placeholder="Quantidade de Estoque" required></input> 
			</div>
			<div class="pure-control-group">
				<label for="valor">Valor(*)</label>
				<input type="text" id="valor" name="valor" class="pure-input-1-4" placeholder="Valor" required></input>
			</div> 
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-input-1-4 pure-button-primary">Cadastrar</button>
			</div>
		</form>
	</div>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>