<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de citas</title>
</head>
<body>

	<h1>Listado de citas</h1>

	<form action="listado" method="post">
		<input type="text" placeholder="Texto" name="texto">
		<input type="datetime-local" placeholder="Inicio" name="inicio">
		<input type="datetime-local" placeholder="Fin" name="fin">
		
		<button>Guardar</button>
	</form>

	<c:forEach items="${citas}" var="c">
		<p><a href="detalle?id=${c.id}">${c.texto}</a> (${c.usuario.nombre}): (${c.inicio} => ${c.fin})</p>
	</c:forEach>

</body>
</html>