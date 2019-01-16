<body class="text-center">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
					<h1 class="masthead-brand">
						<a href="../index.jsp"><img src="../img/fee64.png"></a><small>${sessionScope.agenteLogueado.nombre}</small>
					</h1>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarCollapse" aria-controls="navbarCollapse"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarCollapse">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="btn btn-secondary btn-block m-2" href="matricula">
									Multar</a></li>
							<li class="nav-item"><a class="btn btn-secondary btn-block m-2" href="listado">Ver
									Multas</a></li>
							<li class="nav-item"><a class="btn btn-secondary btn-block m-2" href="multa?op=4">Ver
									Multas Anuladas</a></li>
							<li class="nav-item"><a class="btn btn-danger btn-block m-2" href="${pageContext.request.contextPath}/logout">Cerrar
									sesi�n</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</header>