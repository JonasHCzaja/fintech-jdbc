<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Conta</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Editar Conta</h1>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <form action="usuario" method="post">
            <input type="hidden" name="acao" value="editar">
            <input type="hidden" name="idUsuario" value="${usuario.email}">
            <input type="hidden" name="nome" value="${usuario.nome}">
        
		<div class= "form-group">
			<label for= "id-email">E-mail</label>
			<input type="text" disabled name="email" id="id-email" class="form-control" placeholder="${usuario.email}">
		</div>
		<div class= "form-group">
			<label for= "id-senha">Senha</label>
			<input type="text" name="senha" id="id-senha" class="form-control" placeholder="Nova Senha">
		</div>
            <input type="submit" value="Salvar" class="btn btn-primary">
            <a href="divida?acao=listar" class="btn btn-danger">Cancelar</a>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
    
  
    
</body>
</html>
