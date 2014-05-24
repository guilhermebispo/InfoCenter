<!DOCTYPE html>
<%@ page import="br.com.infoCenter.infra.ProdutoDTO"%>
<%!ProdutoDTO produto;%>
<%
	produto = (ProdutoDTO) request.getAttribute("produto");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Altera��o de Produto</title>
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
    $("#formAlteracaoProduto").validate({
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
        		required: "O campo C�DIGO DE BARRA � obrigat�rio!"
        	},
        	descricao:{
                required: "O campo DESCRI��O � obrigat�rio!"
            },
            dtFabricacao:{
                required: "O campo DATA FABRICA��O � obrigat�rio!"
            },
            marca:{
                required: "O campo MARCA � obrigat�rio!"
            },
            qtdEstoque:{
            	required: "O campo QTD ESTOQUE � obrigat�rio!"
            },
            valor:{
            	required: "O campo VALOR � obrigat�rio!"
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
	<form id="formAlteracaoProduto" action="/InfoCenter/produto" method="post">
		<input type="hidden" value="concluirAlteracao" name="acao"></input> 
		<input type="hidden" value="<%=produto.getIdProduto()%>" name="idProduto"></input>
		C�digo de Barra*: <input type="text" id="codBarra" name="codBarra" value="<%=produto.getCodBarra()%>"></input><br />
		Descri��o*: <input type="text" id="descricacao" name="descricao" value="<%=produto.getDescricao()%>"></input><br /> 
		Data Fabrica��o*: <input type="text" id="dtFabricacao" name="dtFabricacao" value="<%=produto.getDtFabricacao()%>"></input><br />
		Marca*: <input type="text" id="marca" name="marca" value="<%=produto.getMarca()%>"></input><br />
		Observa��o: <input type="text" id="observacao" name="observacao" value="<%=produto.getObs()%>"></input><br /> 
		Qtd Estoque*: <input type="text" id="qtdEstoque" name="qtdEstoque" value="<%=produto.getQtdEstoque()%>"></input><br />
		Valor*: <input type="text" id="valor" name="valor" value="<%=produto.getValorProduto()%>"></input><br />
		<input type="submit" value="Alterar"></input>
	</form>
	<jsp:include page="../_template/template_rodape.jsp" />
</body>
</html>