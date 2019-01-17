<%@include file="../includes/cabecera.jsp" %>
<%@include file="../includes/menu.jsp" %>

<main role="main" class="inner cover text-center">
    <h1 class="cover-heading">
	    <span class="d-flex justify-content-center align-items-center">
	    	<i class="material-icons" style="font-size:64px;">equalizer</i>
	    	Estadísticas:
	    </span>
    </h1>
    <div class="col-xl-12 col-sm-6 py-2">
    	<div class="card bg-success text-white h-100">
    		<div class="card-body bg-success">
    		<div class="rotate">
    			<i class="material-icons" style="font-size:48px;">trending_up</i>
    		</div>
    		<h6 class="text-uppercase">Ganancias mensuales</h6>
    		
			<c:choose>
			  <c:when test="${agenteLogueado.importeMensual <= 700}">
			    <h3 class="display-4 text-danger">${agenteLogueado.importeMensual}&euro; / 1000&euro;</h3>
			  </c:when>
			  <c:when test="${agenteLogueado.importeMensual >= 1000}">
			    <h3 class="display-4">${agenteLogueado.importeMensual}&euro; / 1000&euro;</h3>
			    <small>Has cumplido el objetivo mensual!</small>
			  </c:when>
			  <c:otherwise>
			    <h3 class="display-4 text-warning">${agenteLogueado.importeMensual}&euro; / 1000&euro;</h3>
			    <small class="text-warning">Te quedan 
				    <fmt:formatNumber type = "number" 
	         			maxIntegerDigits = "2" value = "${(1000 - agenteLogueado.importeMensual)}" />
				    	&euro; para cumplir el objetivo mensual.
			    </small>
			  </c:otherwise>
			</c:choose>   		
			
    		</div>
    	</div>
    </div>
    <div class="col-xl-12 col-sm-6 py-2">
    	<div class="card bg-info text-white h-100">
    		<div class="card-body bg-info">
    		<div class="rotate">
    			<i class="material-icons" style="font-size:48px;">trending_up</i>
    		</div>
    		<h6 class="text-uppercase">Ganancias anuales</h6>
    		
    		<c:choose>
			  <c:when test="${agenteLogueado.importeAnual <= 10000}">
			    <h3 class="display-4 text-danger">${agenteLogueado.importeAnual}&euro; / 12000&euro;</h3>
			  </c:when>
			  <c:when test="${agenteLogueado.importeAnual >= 12000}">
			    <h3 class="display-4">${agenteLogueado.importeAnual}&euro; / 12000&euro;</h3>
			    <small>Has cumplido el objetivo anual!</small>
			  </c:when>
			  <c:otherwise>
			    <h3 class="display-4 text-warning">${agenteLogueado.importeAnual}&euro; / 12000&euro;</h3>
			    <small class="text-warning">Te quedan 
				    <fmt:formatNumber type = "number" 
	         			maxIntegerDigits = "2" value = "${(12000 - agenteLogueado.importeAnual)}" />
				    	&euro; para cumplir el objetivo anual.
			    </small>
			  </c:otherwise>
			</c:choose> 
    		
    		</div>
    	</div>
    </div>
<%@include file="../includes/mensajes.jsp" %>
<%@include file="../includes/pie.jsp" %>