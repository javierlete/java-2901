<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario administración</title>
</head>
<body>

	<form action="guardar" method="post">
		<input type="hidden" name="id" value="${cita.id}">
		<select name="id-usuario">
			<option value="0">NINGUNO</option>
			<c:forEach items="${usuarios}" var="u">
				<option value="${u.id}" ${cita.usuario.id == u.id ? 'selected': '' }>${u.nombre}</option>
			</c:forEach>
		</select>
		
		<input type="text" placeholder="Texto" name="texto" value="${cita.texto}">
		<input type="datetime-local" placeholder="Inicio" name="inicio" value="${cita.inicio}">
		<input type="datetime-local" placeholder="Fin" name="fin" value="${cita.fin}">
		
		<button>Guardar</button>
	</form>

</body>
</html>