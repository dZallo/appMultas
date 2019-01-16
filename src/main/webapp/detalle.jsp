<%@include file="includes/cabecera.jsp" %>
<%@include file="includes/menu.jsp" %>

<main role="main" class="inner cover ">
<div class="border border-white pr-2 pl-2 rounded">
   <h3 class="cover-heading text-left ">Detalle multa</h3>
    
	<p class="text-left"><strong>Matrícula: </strong>${multa.coche.matricula}</p>
	<p class="text-left"><strong>Modelo: </strong>${multa.coche.modelo}</p>
  	<p class="text-left"><strong>Importe: </strong>${multa.importe}&euro;</p>
  	<p class="text-left"><strong>Fecha alta: </strong>${multa.fecha}</p>
    <c:if test="${multa.fecha_baja!=null}">
        <p class="text-left"><strong>Fecha baja: </strong>${multa.fecha_baja}</p>
    </c:if>  
   
	<p class="text-left ">
		<strong>Concepto: </strong>  
		<span class=" text-left">  
			${multa.concepto}	
		</span>
	</p>
</div>

  	<div class="form-group">
	    <input type="hidden" id="id_coche" name="id_coche" value="${multa.coche.id }">
	    <input type="hidden" id="id_agente" name="id_agente"  value="${sessionScope.agenteLogueado.id }">
  	</div>
	<c:if test="${multa.fecha_baja==null}">
  	<p class="lead">
      <!-- Button to Open the Modal -->
	<button type="button" class="btn btn-lg btn-warning btn-block" data-toggle="modal" data-target="#myModal">
	 Anular multa
	</button>
	</p>
	</c:if>
		<!-- The Modal -->
		<div class="modal text-body" id="myModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">Atención!</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		        Estás segur@ de que deseas anular la multa?
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
	        	<a href="multa?op=3&id=${multa.id}" class="btn btn-success">Confirmar</a>	      
		      </div>
		    </div>
		  </div>
		</div>
		<c:choose>
    <c:when test="${multa.fecha_baja==null}">
        <p class="lead">
	  	  <a href="listado" class="btn btn-lg btn-info btn-block">Volver al listado</a>
	  	</p> 
    </c:when>    
    <c:otherwise>
        <p class="lead">
	  	  <a href="multa?op=4" class="btn btn-lg btn-info btn-block">Volver al listado</a>
	  	</p>
    </c:otherwise>
</c:choose>
  	
    
<%@include file="includes/pie.jsp" %>