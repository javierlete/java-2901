<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
	<dl>
		<dt>Id</dt>
		<dd>${cita.id}</dd>
		<dt>Texto</dt>
		<dd>${cita.texto}</dd>
		<dt>Inicio</dt>
		<dd>${cita.inicio}</dd>
		<dt>Fin</dt>
		<dd>${cita.fin}</dd>
	</dl>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>