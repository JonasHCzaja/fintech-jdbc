<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Investimentos</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Investimentos</h1>
	<table class="table table-striped">
		<thead>
		<tr>
			<th>Nome do Ativo</th>
			<th>Categoria</th>
			<th>Total Investido</th>
			<th>Preço Médio</th>
			<th>Quantidade</th>
            <th>Ações</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach  var="i" items="${investimentos}">
			<tr>
				<td>${i.nomeAtivo}</td>
				<td>${i.categoriaAtivo}</td>
				<td>R$${i.quantidade * i.precoMedio}</td>
				<td>R$${i.precoMedio}</td>
				<td>${i.quantidade}</td>
				<td>
                    <c:url value="investimento" var="link">
                        <c:param name="acao" value="abrir-form-edicao"/>
                        <c:param name="cdAtivo" value="${i.cdAtivo}"/>
                    </c:url>
                    <a href="${link}" class="btn btn-primary">Editar</a>
                    <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#excluirModal" onclick="codigoExcluir.value=${i.cdAtivo}">
                        Excluir
                    </button>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary register-btn" href="cadastro-investimento.jsp">Cadastrar Novo Investimento</a>
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
                    Deseja realmente excluir o investimento?
                </div>
                <div class="modal-footer">
                    <form action="investimento" method="post">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="cdAtivo" id="codigoExcluir">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-danger">Excluir</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
</html>