<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="card col-4 offset-4">
	<div class="card-header">${cita.texto}</div>
	<div class="card-body">
		<dl class="card-text">
			<dt>Id</dt>
			<dd>${cita.id}</dd>
			<dt>Inicio</dt>
			<dd>${cita.inicio}</dd>
			<dt>Fin</dt>
			<dd>${cita.fin}</dd>
		</dl>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>