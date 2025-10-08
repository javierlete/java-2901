<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="card col-sm-6 offset-sm-3 col-lg-4 offset-lg-4 col-xl-2 offset-xl-5">
	<div class="card-header text-center">${cita.texto}</div>
	<div class="card-body">
		<dl class="card-text text-center">
			<dt>Id</dt>
			<dd>${cita.id}</dd>
			<dt>Inicio</dt>
			<dd><javatime:format value="${cita.inicio}" pattern="dd/MM/yyyy HH:mm"></javatime:format></dd>
			<dt>Fin</dt>
			<dd><javatime:format value="${cita.fin}" pattern="dd/MM/yyyy HH:mm"></javatime:format></dd>
		</dl>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>