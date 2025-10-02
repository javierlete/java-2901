<%@page import="com.ipartek.formacion.citas.entidades.Cita"%>
<%@page import="com.ipartek.formacion.bibliotecas.Fabrica"%>
<%@page import="com.ipartek.formacion.citas.accesodatos.DaoCita"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado</title>
</head>
<body>
	<h1>Listado</h1>

	<%
	DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	%>

	<ul>
		<%
		for (Cita cita : dao.obtenerTodos()) {
		%>
		<li><a href="detalle.jsp?id=<%=cita.getId()%>"><%= cita.getTexto() %></a> (<%=cita.getUsuario() %>): (<%=cita.getInicio() %> => <%=cita.getFin() %></li>
		<%
		}
		%>
	</ul>
</body>
</html>