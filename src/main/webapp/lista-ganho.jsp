<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ganhos</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Ganhos</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Descrição</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th>Data do Recebimento</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="g" items="${ganhos}">
                    <tr>
                        <td>${g.descricao}</td>
                        <td>${g.categoria}</td>
                        <td>R$${g.valor}</td>
                        <td><fmt:formatDate value="${g.dtRecebimento.time}" pattern="dd/MM/yyyy"/></td>
                        <td>
                            <c:url value="ganho" var="link">
                                <c:param name="acao" value="abrir-form-edicao"/>
                                <c:param name="cdGanho" value="${g.cdGanho}"/>
                            </c:url>
                            <a href="${link}" class="btn btn-primary">Editar</a>
                            <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#excluirModal" onclick="codigoExcluir.value=${g.cdGanho}">
                                Excluir
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="btn btn-primary register-btn" href="cadastro-ganho.jsp">Cadastrar Novo Ganho</a>
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
                    Deseja realmente excluir o ganho?
                </div>
                <div class="modal-footer">
                    <form action="ganho" method="post">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="cdGanho" id="codigoExcluir">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-danger">Excluir</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

   

</body>
</html>
