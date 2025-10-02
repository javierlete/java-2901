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

	<h1>Detalle de cita</h1>

	<p>${cita.id}</p>
	<p>${cita.texto}</p>
	<p>${cita.usuario.nombre}</p>
	<p>${cita.inicio}</p>
	<p>${cita.fin}</p>

</body>
</html>