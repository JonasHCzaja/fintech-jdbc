<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dividas</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Dividas</h1>
	<table class="table table-striped">
	<thead>
		<tr>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Prazo para Pagamento</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach  var="d" items="${dividas}">
			<tr>
				<td>${d.descricao}</td>
				<td>R$${d.valor}</td>
				<td><fmt:formatDate value="${d.prazo.time}" pattern="dd/MM/yyyy"/></td>
				<td>
					<c:url value="divida" var="link">
                        <c:param name="acao" value="abrir-form-edicao"/>
                        <c:param name="cdDivida" value="${d.cdDivida}"/>
                    </c:url>
                    <a href="${link}" class="btn btn-primary">Editar</a>
                    <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#excluirModal" onclick="codigoExcluir.value=${d.cdDivida}">
                        Excluir
                    </button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<a class="btn btn-primary register-btn" href="cadastro-divida.jsp">Cadastrar Nova Divida</a>
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
                    Deseja realmente excluir a divida?
                </div>
                <div class="modal-footer">
                    <form action="divida" method="post">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="cdDivida" id="codigoExcluir">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-danger">Excluir</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
</html>