<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Despesas</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
	<h1>Despesas</h1>
	<table class="table table-striped">
	<thead>
		<tr>
			<th>Descrição</th>
			<th>Categoria</th>
			<th>Tipo de Despesa</th>
			<th>Valor</th>
			<th>Data da Despesa</th>
			<th>Cartão de Crédito</th>
			<th>Ações</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${despesas}" var="d">
			<tr>
				<td>${d.descricao}</td>
				<td>${d.categoria}</td>
				<td>${d.tipoDespesa}</td>
				<td>R$${d.valor}</td>
				<td><fmt:formatDate value="${d.dtDespesa.time}" pattern="dd/MM/yyyy"/></td>
				<td>${d.dsCartao}</td>
				<td>
                    <c:url value="despesa" var="link">
                        <c:param name="acao" value="abrir-form-edicao"/>
                        <c:param name="cdDespesa" value="${d.cdDespesa}"/>
                    </c:url>
                    <a href="${link}" class="btn btn-primary">Editar</a>
                    <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#excluirModal" onclick="codigoExcluir.value=${d.cdDespesa}">
                        Excluir
                    </button>				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary register-btn" href="cadastro-despesa.jsp">Cadastrar Nova Despesa</a>
</div>

<%@ include file="footer.jsp" %>

     <!-- Modal -->
    <div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Deseja realmente excluir a despesa?
                </div>
                <div class="modal-footer">
                    <form action="despesa" method="post">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="cdDespesa" id="codigoExcluir">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-danger">Excluir</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
</html>