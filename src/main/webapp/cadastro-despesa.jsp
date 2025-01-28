<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Despesas</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastrar Despesa</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${ msg }</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${ erro }</div>
	</c:if>
	<form action="despesa" method= "post">
	<input type="hidden" value="cadastrar" name="acao">
		<div class= "form-group">
			<label for= "id-descricao">Descrição da Despesa</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control">
		</div>
		<div class= "form-group">
			<label for= "id-categoria">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0"></option>
				<option value="OUTROS">OUTROS</option>
				<option value="CASA">CASA</option>
				<option value="ASSINATURA">ASSINATURA</option>
				<option value="SAÚDE">SAÚDE</option>
				<option value="TRANSPORTE">TRANSPORTE</option>
				<option value="MERCADO">MERCADO</option>
				<option value="ALIMENTAÇÃO">ALIMENTAÇÃO</option>
				<option value="LAZER">LAZER</option>
				<option value="EDUCAÇÃO">EDUCAÇÃO</option>
				
			</select>
			
		
		</div>
		<div class= "form-group">
			<label for= "id-tipo">Tipo de Despesa</label>
			<select name="tipoDespesa" id="id-tipo" class="form-control">
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
			<label for= "id-data">Data da Despesa</label>
			<input type="text" name="dtDespesa" id="id-data" class="form-control">
		</div>			
		<div class= "form-group">
		<label for= "id-dsCartao">Cartão de Crédito</label>
		<input type="text" name="dsCartao" id="id-dsCartao" class="form-control">
		</div>
			
		
		<input type="submit" value="Salvar" class=" btn btn-primary">
	</form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>