<%@include file="../includes/cabecera.jsp"%>

<body class="text-center">

	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<h1 class="cover-heading">ERROR 404</h1>
			</div>
		</header>
		<main role="main" class="inner cover">
		<h2 class="cover-heading">
			<i class="material-icons" style="font-size: 114px;">error </i>
		</h2>
		<h5>Lo sentimos, la página a la que intenta acceder no existe o
			su dirección ha sido modificada :(</h5>
		<div class="error-actions">
			<a href="index" class="btn btn-primary btn-lg"> <span><i
					class="material-icons" style="font-size: 40px;">home</i><br>Inicio</span>
			</a> <a href="index" class="btn btn-warning btn-lg"> <span><i
					class="material-icons" style="font-size: 40px;">contact_mail</i><br>Soporte</span>
			</a>
		</div>

		<%@include file="../includes/pie.jsp"%>