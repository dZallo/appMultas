<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>
<%@include file="includes/mensajes.jsp" %>
<main role="main" class="inner cover">
    <h1 class="cover-heading">Registrar multa</h1>
    <p class="lead">Introduzca la matrícula del vehículo: </p>
    
    <form action="matricula" method="POST">
    <div class="form-group">
    <input type="text" class="form-control" id="matriculaCoche" name="matriculaCoche" 
    	aria-describedby="matriculaHelp" placeholder="P.E: 1234AAA" value ="${matricula}" autofocus>
  	</div>
      <button type="submit" class="btn btn-lg btn-secondary">Buscar</button>
  	</form>
    
<%@include file="includes/pie.jsp" %>