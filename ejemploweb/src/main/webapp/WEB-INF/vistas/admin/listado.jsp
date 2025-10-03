<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado administración</title>
</head>
<body>

	<table style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Usuario</th>
				<th>Texto</th>
				<th>Inicio</th>
				<th>Fin</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${citas}" var="c">
				<tr>
					<th>${c.id}</th>
					<td>${c.usuario.nombre}</td>
					<td>${c.texto}</td>
					<td>${c.inicio}</td>
					<td>${c.fin}</td>
					<td>
						<a href="editar?id=${c.id}">Editar</a>
						<a href="borrar?id=${c.id}">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"></td>
				<td>
					<a href="anyadir">Añadir</a>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>