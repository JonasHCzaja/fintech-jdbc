<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Divida</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Editar Divida</h1>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <form action="divida" method="post">
            <input type="hidden" name="acao" value="editar">
            <input type="hidden" name="cdDivida" value="${divida.cdDivida}">
            <div class= "form-group">
				<label for= "id-valor">Valor</label>
				<input type="text" name="valor" id="id-valor" class="form-control" value="${divida.valor}">
			</div>
			<div class= "form-group">
				<label for= "id-descricao">Descrição</label>
				<input type="text" name="descricao" id="id-descricao" class="form-control" value="${divida.descricao}">
			</div>
            <div class="form-group">
                <label for="id-prazo">Prazo</label>
                <input type="text" name="prazo" id="id-prazo" class="form-control" 
                    value="<fmt:formatDate value='${divida.prazo.time}' pattern='dd/MM/yyyy'/>">
            </div>
            <input type="submit" value="Salvar" class="btn btn-primary">
            <a href="divida?acao=listar" class="btn btn-danger">Cancelar</a>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
