<%@include file="../includes/cabecera.jsp" %>
<%-- <%@include file="includes/menu.jsp" %> --%>
<body class="text-center">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="masthead mb-auto">
 
    <div class="inner">
      <h3 class="masthead-brand"><a href="index"><img src="img/fee64.png"></a>${sessionScope.agenteLogueado.nombre}</h3>
     </div>
  </header>
<main role="main" class="inner cover">
    <h1 class="cover-heading"><i class="material-icons" style="font-size:114px;">directions_car</i></h1>
    <p class="lead">Seleccione la acci�n a realizar: </p>
    <p class="lead">
      <a href="privado/matricula" class="btn btn-lg btn-secondary btn-block">Multar</a>
    </p>
    <p class="lead">
      <a href="privado/listado" class="btn btn-lg btn-secondary btn-block">Ver Multas</a>
    </p>
    <p>
      <a href="privado/multa?op=4" class="btn btn-lg btn-secondary btn-block">Ver Multas Anuladas</a>
    </p>
    <p>
      <a href="logout" class="btn btn-lg btn-danger btn-block">Cerrar sesi�n</a>
    </p>
<%@include file="../includes/mensajes.jsp" %>
<%@include file="../includes/pie.jsp" %>