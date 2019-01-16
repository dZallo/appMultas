<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>
<%@include file="includes/mensajes.jsp" %>
<main role="main" class="inner cover">
    <h3 class="cover-heading">Registrar multa</h3>
    <p class="lead"><small>Introduzca la matr�cula del veh�culo: </small></p>
    
    <form action="altamulta" method="POST">
    <div class="form-group">	    
	    <label for="matricula">Matr�cula: </label>
	    <input type="text" class="form-control" id="matricula" name="matricula" aria-describedby="matriculaHelp" placeholder="P.E: 1234AAA" value="${coche.matricula}" disabled>
  	</div>
  	<div class="form-group">	    
	    <label for="modelo">Modelo: </label>
	    <input type="text" class="form-control" id="modelo" name="modelo" aria-describedby="modeloHelp" placeholder="P.E: Ford Scort" value="${coche.modelo}" disabled>
  	</div>
  	<div class="form-group">
	    <label for="importe">Importe: </label>
	    <input type="number" class="form-control" id="importe" name="importe" aria-describedby="importeHelp" placeholder="P.E: 300" value="${importe}" autofocus step="any" required>
  	</div>
  	<div class="form-group">
	    <label for="concepto">Concepto: </label>
	    <textarea class="form-control" id="concepto" name="concepto" aria-describedby="importeHelp" placeholder="Motivo de la sanci�n" required>${concepto}</textarea>
  	</div>
  	<div class="form-group">
	    <input type="hidden" id="id_coche" name="id_coche" value="${coche.id }">
	    <input type="hidden" id="id_agente" name="id_agente"  value="${sessionScope.agenteLogueado.id }">
  	</div>
      <button type="submit" class="btn btn-lg btn-secondary btn-block">Crear</button>
  	</form>
    
<%@include file="includes/pie.jsp" %>