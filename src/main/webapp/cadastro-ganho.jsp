<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Ganhos</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastrar Ganho</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${ msg }</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${ erro }</div>
	</c:if>
	<form action="ganho" method="post">
		<input type="hidden" value="cadastrar" name="acao">
		<div class= "form-group">
			<label for= "id-descricao">Descrição</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control">
		</div>
		<div class= "form-group">
			<label for= "id-categoria">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0"></option>
				<option value="RECORRENTE">RECORRENTE</option>
				<option value="VARIÁVEL">VARIÁVEL</option>
			</select>
		</div>
		<div class= "form-group">
			<label for= "id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control">
		</div>
		<div class= "form-group">
			<label for= "id-recebimento">Data de Recebimento</label>
			<input type="text" name="dtRecebimento" id="id-recebimento" class="form-control">
		</div>
		<input type="submit" value="Salvar" class=" btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>