<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>
<%@include file="includes/mensajes.jsp" %>
<main role="main" class="inner cover">
    <h1 class="cover-heading">Registrar multa</h1>
    <p class="lead">Introduzca la matrícula del vehículo: </p>
    
    <form action="altamulta" method="POST">
    <div class="form-group">
	    <label for="matricula">Numero bastidor</label>
	    <input type="text" class="form-control" id="matricula" name="matricula" aria-describedby="matriculaHelp" placeholder="P.E: 1234AAA" value="${coche.matricula}" disabled>
  	</div>
  	<div class="form-group">
	    <label for="importe">Importe</label>
	    <input type="number" class="form-control" id="importe" name="importe" aria-describedby="importeHelp" placeholder="P.E: 300" value="${importe}">
  	</div>
  	<div class="form-group">
	    <label for="concepto">Concepto</label>
	    <textarea class="form-control" id="concepto" name="concepto" aria-describedby="importeHelp" placeholder="Motivo de la sanción">${concepto}</textarea>
  	</div>
  	<div class="form-group">
	    <input type="hidden" id="id_coche" name="id_coche" value="${coche.id }">
	    <input type="hidden" id="id_agente" name="id_agente"  value="${sessionScope.agenteLogueado.id }">
  	</div>
      <button type="submit" class="btn btn-lg btn-secondary">Crear</button>
  	</form>
    
<%@include file="includes/pie.jsp" %>