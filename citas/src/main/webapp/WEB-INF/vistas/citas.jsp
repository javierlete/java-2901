<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<ul class="list-group col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-4 offset-lg-4">
	<c:forEach items="${citas}" var="c">
		<li
			class="list-group-item d-flex justify-content-between align-items-start">
			<div class="ms-2 me-auto">
				<div class="fw-bold">
					<a href="detalle?id=${c.id}">${c.texto}</a>
				</div>
				
			</div>
			<div>
				<p><javatime:format value="${c.inicio}" pattern="dd/MM/yyyy HH:mm"></javatime:format></p>
				<p><javatime:format value="${c.fin}" pattern="dd/MM/yyyy HH:mm"></javatime:format></p>
			</div>
		</li>
	</c:forEach>
</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
