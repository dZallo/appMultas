<body class="text-center">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
					<h3 class="masthead-brand">
						<a href="index"><img src="img/fee64.png"></a>${sessionScope.agenteLogueado.nombre}</h3>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarCollapse" aria-controls="navbarCollapse"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarCollapse">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="btn btn-secondary btn-block m-1" href="matricula">
									Multar</a></li>
							<li class="nav-item"><a class="btn btn-secondary btn-block m-1" href="listado">Ver
									Multas</a></li>
							<li class="nav-item"><a class="btn btn-secondary btn-block m-1" href="multa?op=4">Ver
									Multas Anuladas</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</header>