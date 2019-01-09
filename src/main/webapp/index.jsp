<%@include file="includes/cabecera.jsp" %>
<%-- <%@include file="includes/menu.jsp" %> --%>
<body class="text-center">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="masthead mb-auto">
    <div class="inner">
      <h3 class="masthead-brand">${sessionScope.agenteLogueado.nombre}</h3>
     </div>
  </header>
<main role="main" class="inner cover">
    <h1 class="cover-heading"><i class="material-icons" style="font-size:114px;">directions_car</i></h1>
    <p class="lead">Seleccione la acción a realizar: </p>
    <p class="lead">
      <a href="matricula" class="btn btn-lg btn-secondary">Multar</a>
    </p>
    <p class="lead">
      <a href="listado" class="btn btn-lg btn-secondary">Ver Multas</a>
    </p>
    <%@include file="includes/mensajes.jsp" %>
<%@include file="includes/pie.jsp" %>