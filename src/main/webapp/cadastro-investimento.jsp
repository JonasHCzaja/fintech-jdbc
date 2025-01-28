<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Investimentos</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastrar Investimento</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${ msg }</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${ erro }</div>
	</c:if>
	<form action="investimento" method= "post">
		<input type="hidden" value="cadastrar" name="acao">
		<div class= "form-group">
			<label for= "id-ativo">Nome do Ativo</label>
			<input type="text" name="nomeAtivo" id="id-ativo" class="form-control">
		</div>
		<div class= "form-group">
			<label for= "id-categoria">Categoria</label>
			<select name="categoriaAtivo" id="id-categoria" class="form-control">
				<option value="0"></option>
				<option value="AÇÃO">AÇÃO</option>
				<option value="FII">FII</option>
				<option value="RENDA FIXA">RENDA FIXA</option>
				<option value="CRIPTOMOEDA">CRIPTOMOEDA</option>
				<option value="ETF">ETF</option>
				<option value="BDR">BDR</option>
			</select>
		</div>
		<div class= "form-group">
			<label for= "id-preco">Preço Médio</label>
			<input type="text" name="precoMedio" id="id-preco" class="form-control">
		</div>
		<div class= "form-group">
			<label for= "id-quantidade">Quantidade</label>
			<input type="text" name="quantidade" id="id-quantidade" class="form-control">
		</div>
		<input type="submit" value="Salvar" class=" btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>