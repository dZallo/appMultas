<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>
<%@include file="includes/mensajes.jsp" %>

<main role="main" class="inner cover">
    <h1 class="cover-heading">Multas anuladas</h1>
    
    
<table id="tabla_multas" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
        <thead>
		    <tr>
		      <th scope="col">Matrícula</th>
		      <th scope="col">Fecha</th>
		      <th scope="col">Concepto</th>
		      <th scope="col">Importe</th>
		    </tr>
	  </thead>
  <tbody>
  <c:forEach items="${multas}" var="multa">
    <tr>
      <th scope="row">${multa.value.coche.matricula}</th>
      <td>${multa.value.fecha}</td>
      <td>${multa.value.concepto}</td>
      <td>${multa.value.importe}&euro;</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<p class="lead">
<a href="listado" class="btn btn-lg btn-info">Listado de multas</a>
</p>
    
<%@include file="includes/pie.jsp" %>