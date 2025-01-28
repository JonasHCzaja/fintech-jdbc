<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom">

  <a class="navbar-logo" href="home.jsp">
    <svg width="128" height="23" viewBox="0 0 128 23" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M0.272 22V0.655998H14.8V4.656H5.456V9.744H13.52V13.808H5.456V22H0.272ZM18.522 22V0.655998H23.706V22H18.522ZM28.2408 22V0.655998H32.6568L43.6328 15.44L42.0968 18.64V0.655998H46.8648V22H42.9608L31.7928 7.408L33.0088 5.072V22H28.2408ZM55.877 22V4.656H49.893V0.655998H67.141V4.656H61.061V22H55.877ZM70.1783 22V0.655998H84.8022V4.656H75.3623V9.296H83.5543V13.328H75.3623V18H84.8022V22H70.1783ZM98.587 22.416C95.1737 22.416 92.5603 21.52 90.747 19.728C88.955 17.9147 88.059 15.12 88.059 11.344C88.059 7.67467 88.923 4.90133 90.651 3.024C92.379 1.12533 95.0243 0.175999 98.587 0.175999C99.7177 0.175999 100.859 0.314665 102.011 0.591999C103.163 0.869333 104.283 1.25333 105.371 1.744V6.256C104.262 5.53067 103.195 5.008 102.171 4.688C101.147 4.368 100.187 4.208 99.291 4.208C98.0323 4.208 96.9657 4.432 96.091 4.88C95.2377 5.328 94.5763 6.07467 94.107 7.12C93.659 8.16533 93.435 9.57333 93.435 11.344C93.435 13.1147 93.659 14.512 94.107 15.536C94.5763 16.56 95.259 17.296 96.155 17.744C97.0723 18.1707 98.1923 18.384 99.515 18.384C100.304 18.384 101.2 18.2347 102.203 17.936C103.206 17.6373 104.262 17.1253 105.371 16.4V20.912C104.774 21.2107 104.091 21.4773 103.323 21.712C102.576 21.9253 101.787 22.096 100.955 22.224C100.144 22.352 99.355 22.416 98.587 22.416ZM109.303 22V0.655998H114.487V9.296H122.327V0.655998H127.479V22H122.327V13.52H114.487V22H109.303Z" fill="white"/>
    </svg>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="ganho?acao=listar">Ganhos</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="investimento?acao=listar">Investimentos</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="despesa?acao=listar">Despesas</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="divida?acao=listar">Dividas</a>
      </li>
    </ul>
    
    <c:if test="${empty user}">
    
    	<span class="navbar-text text-danger" style="margin-right:10px">${erro}</span>
    <form class="form-inline my-2 my-lg-0" action="usuario" method="post">
    	<input type="hidden" name="acao" value="login">
    	<input class="form-control mr-sm-2" type="text" name="email" placeholder="E-mail">
    	<input class="form-control mr-sm-2" type="password" name="senha" placeholder="Senha">
    	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
    	<a class="btn btn-primary" href="cadastro-usuario.jsp" style="margin-left:5px">Criar Conta</a>
    </form>
    
    </c:if>
    <c:if test="${not empty user}">
    <ul class="navbar-nav py-profile justify-content-end">
        <li class="nav-item dropdown">
          <a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle" role="button" aria-haspopup="true" aria-expanded="false">
           Ola ${user}!
          </a>
          <div class="dropdown-menu dropdown-menu-right">
          	<c:url value="usuario" var="link">
                        <c:param name="acao" value="abrir-form-edicao"/>
                        <c:param name="idUsuario" value="${user}"/>
                    </c:url>
            <a href="${link}" class="dropdown-item">Editar Perfil</a>
            <div class="dropdown-divider"></div>
            <a href="usuario?acao=logout" class="dropdown-item text-danger">Sair</a>
          </div>
        </li>
      </ul>
      </c:if>
  </div>
</nav>