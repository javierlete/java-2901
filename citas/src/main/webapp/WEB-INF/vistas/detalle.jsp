<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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

</body>
</html>