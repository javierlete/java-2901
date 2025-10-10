<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<c:if test="${error != null}">
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		${error}
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
</c:if>

<p>${cita}</p>

<form action="admin/guardar" method="post">
	<input type="hidden" name="id" value="${cita.id}">
	<div class="row mb-3">
		<label for="texto" class="col-sm-2 col-form-label">Texto</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="texto" name="texto"
				value="${cita.texto}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="inicio" class="col-sm-2 col-form-label">Fecha inicio</label>
		<div class="col-sm-10">
			<input type="datetime-local" class="form-control" id="inicio" name="inicio"
				value="${cita.inicio}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="fin" class="col-sm-2 col-form-label">Fecha fin</label>
		<div class="col-sm-10">
			<input type="datetime-local" class="form-control" id="fin" name="fin"
				value="${cita.fin}">
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm-10">
			<button type="submit" class="btn btn-primary">Guardar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
