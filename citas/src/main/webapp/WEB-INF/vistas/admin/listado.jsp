<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-hover table-bordered">
	<thead class="table-secondary">
		<tr>
			<th class="text-end">Id</th>
			<th>Texto</th>
			<th>Inicio</th>
			<th>Fin</th>
			<th>Opciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${citas}" var="c">
			<tr>
				<th class="text-end">${c.id}</th>
				<td>${c.texto}</td>
				<td><javatime:format value="${c.inicio}" pattern="dd/MM/yyyy HH:mm"></javatime:format></td>
				<td><javatime:format value="${c.fin}" pattern="dd/MM/yyyy HH:mm"></javatime:format></td>
				<td>
					<a class="btn btn-sm btn-primary" href="admin/formulario?id=${c.id}">Editar</a>
					<a class="btn btn-sm btn-danger" href="admin/borrar?id=${c.id}" onclick="return confirm('¿Estás seguro de borrar la cita ${c.id}?')">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot class="table-secondary">
		<tr>
			<td colspan="4"></td>
			<td>
				<a class="btn btn-sm btn-primary" href="admin/formulario">Añadir</a>
			</td>
		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
