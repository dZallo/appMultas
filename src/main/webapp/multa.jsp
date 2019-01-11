<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>

<main role="main" class="inner cover">
   <h1 class="cover-heading">Visualizar multa</h1>
   
   <form action="#" method="POST">
    <div class="form-group">	    
	    <label for="matricula">Matrícula</label>
	    <input type="text" class="form-control" id="matricula" name="matricula" aria-describedby="matriculaHelp" placeholder="P.E: 1234AAA" value="${multa.coche.matricula}" disabled>
  	</div>
  	<div class="form-group">
	    <label for="importe">Importe</label>
	    <input type="number" class="form-control" id="importe" name="importe" aria-describedby="importeHelp" placeholder="P.E: 300" value="${multa.importe}" disabled>
  	</div>
  	<div class="form-group">
	    <label for="concepto">Concepto</label>
	    <textarea class="form-control" id="concepto" name="concepto" aria-describedby="importeHelp" placeholder="Motivo de la sanción" disabled>${multa.concepto}</textarea>
  	</div>
  	<div class="form-group">
	    <input type="hidden" id="id_coche" name="id_coche" value="${multa.coche.id }">
	    <input type="hidden" id="id_agente" name="id_agente"  value="${sessionScope.agenteLogueado.id }">
  	</div>
  	<p class="lead">
  	  <a href="listado" class="btn btn-lg btn-info">Volver al listado</a>
  	</p>
  	<p class="lead">
      <a href="anular" class="btn btn-lg btn-warning">Anular multa</a>
    </p>
  	</form>
    
<%@include file="includes/pie.jsp" %>