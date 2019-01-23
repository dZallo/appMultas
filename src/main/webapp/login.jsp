<%@include file="includes/cabecera.jsp" %>
<body class="text-center">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
<main role="main" class="inner cover">
    <h1 class="cover-heading"><i class="material-icons" style="font-size:114px;">directions_car</i></h1>
    <p class="lead">Introduzca sus credenciales: </p>
    <form action="login" method="POST">
    <div class="form-group">	    
	    <label for="placa">Nº Placa: </label>
	    <input type="number" class="form-control" id="placa" name="placa" 
	    	   aria-describedby="placaHelp" placeholder="P.E: 1234" value="${placa}" required autofocus>
  	</div>
  	<div class="form-group">	    
	    <label for="password">Contraseña: </label>
	    <input type="password" class="form-control" id="password" name="password" 
	    	   aria-describedby="passwordHelp" placeholder="P.E: 12345678" value="${password}" required>
  	</div>
  	<button type="submit" class="btn btn-lg btn-secondary btn-block mb-3">
  		<span class="d-flex justify-content-center align-items-center">Acceder
      		<i class="material-icons" style="font-size:26px; margin-left:3px;">input</i>
      	</span>
    </button>
  	</form>
<%@include file="includes/mensajes.jsp" %>
<%@include file="includes/pie.jsp" %>