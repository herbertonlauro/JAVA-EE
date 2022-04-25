<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaModel"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
    

<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"rel="stylesheet">
<script type="text/javascript" src="scripts/validarFormulario.js"></script>
</head>
<body>
	<div class="main-block">
		<div class="left-part">
			<i class="fas fa-envelope"></i> 
			<i class="fas fa-at"></i> 
			<i class="fas fa-mail-bulk"></i>
		</div>

		<form name="FormCadastro" action="update">
			<h1>Agenda de Contatos</h1>
			<p>Editar Contatos</p>
			<div class="info">
			    <label >ID</label>
			    <input type="text" name="idcon"  value="<%out.print(request.getAttribute("idcon"));%>" readonly> 
			    <label >Nome</label>
                <input type="text" name="nome"  value="<%out.print(request.getAttribute("nome"));%>" required>
                <label>Email</label>
			    <input type="text" name="email"  value="<%out.print(request.getAttribute("email"));%>" required> 
			    <label >Fone</label>
			    <input type="text"  name="fone"  value="<%out.print(request.getAttribute("fone"));%>" required>

			</div>
			<input type="submit" class="botao" value="SALVAR" onclick=Validar()>
			<input type="button" value="CANCELAR"onclick="window.location.href='main';"/> 
		</form>
	</div>
	
</body>
</html>