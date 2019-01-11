<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>

<main role="main" class="inner cover">
    <h1 class="cover-heading">Todas las multas</h1>
    
    
<table id="tabla_multas" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
        <thead>
		    <tr>
		      <th scope="col">Matrícula</th>
		      <th scope="col">Fecha</th>
		    </tr>
	  </thead>
  <tbody>
  <c:forEach items="${multas}" var="multa">
    <tr>
      <th scope="row">${multa.value.coche.matricula}</th>
      <td><a href="multa?op=2&id=${multa.key}">${multa.value.fecha}</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
    
<%@include file="includes/pie.jsp" %>