<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<ul>
	<c:forEach items="${citas}" var="c">
		<li><a href="detalle?id=${c.id}">${c.texto}</a>: ${c.inicio} =>
			${c.fin}</li>
	</c:forEach>
</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
