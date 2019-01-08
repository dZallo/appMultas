<c:if test="${not empty mensaje.alerta}">
	<div class="alert alert-${mensaje.tipo}">
	  ${mensaje.alerta}
	</div>
</c:if>