<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Investimento</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Editar Investimento</h1>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <form action="investimento" method= "post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" name="cdAtivo" value="${investimento.cdAtivo}">
		<div class= "form-group">
			<label for= "id-ativo">Nome do Ativo</label>
			<input type="text" name="nomeAtivo" id="id-ativo" class="form-control" value="${investimento.nomeAtivo}">
		</div>
		<div class= "form-group">
			<label for= "id-categoria">Categoria</label>
			<select name="categoriaAtivo" id="id-categoria" class="form-control">
				<option value="${investimento.categoriaAtivo}">${investimento.categoriaAtivo}</option>
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
			<input type="text" name="precoMedio" id="id-preco" class="form-control" value="${investimento.precoMedio}">
		</div>
		<div class= "form-group">
			<label for= "id-quantidade">Quantidade</label>
			<input type="text" name="quantidade" id="id-quantidade" class="form-control" value="${investimento.quantidade}">
		</div>
		<input type="submit" value="Salvar" class=" btn btn-primary">
        <a href="investimento?acao=listar" class="btn btn-danger">Cancelar</a>
	</form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
