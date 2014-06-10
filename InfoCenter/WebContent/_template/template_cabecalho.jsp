<!DOCTYPE html>
<%@ page import="br.com.infoCenter.infra.ClienteDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[InfoCenter] Menu</title>
	<link rel="stylesheet" href="/InfoCenter/css/pure.css">
	<link rel="stylesheet" href="/InfoCenter/css/main-grid.css">
	<link rel="stylesheet" href="/InfoCenter/css/layouts/pricing.css">

	<script>
	    
	(function (root) {
	// -- Namespaces --
	
	// -- Data --
	root.YUI_config = {"gallery":"gallery-2013.03.27-22-06","combine":true,"filter":"min"};
	}(this));
	
	</script>
	
	<script src="/InfoCenter/js/yui-min.js"></script>
	<script>
	YUI().use('node-base', 'node-event-delegate', function (Y) {
	    // This just makes sure that the href="#" attached to the <a> elements
	    // don't scroll you back up the page.
	    Y.one('body').delegate('click', function (e) {
	        e.preventDefault();
	    }, 'a[href="#"]');
	});
	</script>
</head>
<body>

	<%
		ClienteDTO usuarioLogado = (ClienteDTO) session
				.getAttribute("usuarioLogado");
	%>
	
	<div class="pure-g-r">
	<div class="pure-u-5-6">
	
	<div id="div_menu" class="pure-menu pure-menu-open pure-menu-horizontal">
	    <a href="/InfoCenter/index.jsp" class="pure-menu-heading"><img alt="InfoCenter" src="/InfoCenter/imagens/logomarca.png"></a>
	    <ul id="ul_itens">
	        <li><a href="/InfoCenter/login.jsp">Login</a></li>
	        <li class="pure-menu-selected"><a href="/InfoCenter/venda?acao=listar">Produtos</a></li>
	        <li><a href="/InfoCenter/venda?acao=listarCarrinho">Carrinho de Compras</a></li>
       		<% 
			if (usuarioLogado != null && usuarioLogado.isAdministrador()) {
				out.print("<li><a href='#'>Mais Opções</a><ul>");
				out.print("<li><a href='/InfoCenter/_cliente/cliente_cadastrar.jsp'>Cadastrar Cliente</a></li>");
				out.print("<li><a href='/InfoCenter/_cliente/cliente_consultar.jsp'>Consultar Cliente</a></li>");
				out.print("<li><a href='/InfoCenter/cliente?acao=listar'>Listar Cliente</a></li>");
				out.print("<li class='pure-menu-separator'></li>");
				out.print("<li><a href='/InfoCenter/_produto/produto_cadastrar.jsp'>Cadastrar Produto</a></li>"); 
				out.print("<li><a href='/InfoCenter/_produto/produto_consultar.jsp'>Consultar Produto</a></li>"); 
				out.print("<li><a href='/InfoCenter/produto?acao=listar'>Listar Produto</a></li>");
				out.print("<li class='pure-menu-separator'></li>");
				out.print("<li><a href='/InfoCenter/pagamento?acao=emitirNota'>Emitir Nota</a></li>");
				out.print("</ul></li>");
			}
			%>

	    </ul>
	</div>

	<script>
	YUI({
	    classNamePrefix: 'pure'
	}).use('gallery-sm-menu', function (Y) {
	
	    var horizontalMenu = new Y.Menu({
	        container         : '#div_menu',
	        sourceNode        : '#ul_itens',
	        orientation       : 'horizontal',
	        hideOnOutsideClick: false,
	        hideOnClick       : false
	    });
	
	    horizontalMenu.render();
	    horizontalMenu.show();
	
	});
	</script>
	
	
	</div>
	
	<div id="divUsuarioLogado" class="pure-u-1-6 information-head" align="right">
		<span style="font-size:12px">
		<%
			if (usuarioLogado != null) {
				out.print("Olá " + usuarioLogado.getNome() + "! <a href='/InfoCenter/login?acao=sair'>(Sair)</a>  ");
			}
		%>
		</span>
	</div>
	</div>
	
</body>
</html>