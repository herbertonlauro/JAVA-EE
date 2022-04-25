<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaModel"%>
<%@ page import="java.util.ArrayList"%>
<%
	@SuppressWarnings ("unchecked")
	ArrayList<JavaModel> lista = (ArrayList<JavaModel>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="imagens/favicon.png">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
</head>
<body>

	<div class="main-block">
	
		<div class="left-part">
			<i class="fas fa-envelope"></i> <i class="fas fa-at"></i>
			<i class="fas fa-mail-bulk"></i>
		</div>

		<div class="conteudo">
		<h1>Lista de Contatos</h1>
		<input  class="botaoSAIR" value="SAIR" onclick="window.location.href='index.html';">
		<input  class="botaoRelatorio" value="GERAR RELATORIO" onclick="window.location.href='relatorio';">
		<input  class="botaoadicionar" value="ADICIONAR NOVO" onclick="window.location.href='cadastroNovo.html';">
		 
		 <table id="tabela">
		 <thead>
			<tr>
			  <th>Nome</th>
			  <th>Fone</th>
			  <th>E-mail</th>
			  <th colspan="2">Acão</th>
			</tr>
     <%for (int i =0; i < lista.size();i++) {%>
			<tr>
			    <td><%=lista.get(i).getNome() %></td>
			    <td><%=lista.get(i).getFone() %></td>
			    <td><%=lista.get(i).getEmail() %></td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="botaoEditar">Editar</a></td>
				<td><a href="javascript: confirmExclusao(<%=lista.get(i).getIdcon() %>)" class="botaoExcluir">Excluir</a></td>
			</tr>
				<%} %>
				
				
		</thead>
			
	    </table>
	    <br/>	
		</div>
			
</div>
<script type="text/javascript" src="scripts/confirmacao.js"></script>
</body>
</html>