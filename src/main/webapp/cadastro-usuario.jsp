<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuario</title>
<%@ include file="header.jsp" %>
</head>
<body>
 <%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastro de Usuario</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${ msg }</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${ erro }</div>
	</c:if>
	<form action="usuario" method= "post">
	<input type="hidden" value="cadastrar" name="acao">
		<div class= "form-group">
			<label for= "id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control" placeholder="Seu nome ou apelido">
		</div>
		<div class= "form-group">
			<label for= "id-email">E-mail</label>
			<input type="text" name="email" id="id-email" class="form-control" placeholder="email@exemplo.com">
		</div>
		<div class= "form-group">
			<label for= "id-senha">Senha</label>
			<input type="text" name="senha" id="id-senha" class="form-control" placeholder="Senha">
		</div>
		<input type="submit" value="Salvar" class=" btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>