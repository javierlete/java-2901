<%@page import="java.util.Optional"%>
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
	<h1>Detalle</h1>

	<%
	DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	%>


	<%
		Long id = Long.parseLong(request.getParameter("id"));
		Optional<Cita> oCita = dao.obtenerPorId(id);
		%>
	<% if(oCita.isPresent()) {
		Cita cita = oCita.get();
	%>
	<dl>
		<dt><%=cita.getId() %>:
			<%=cita.getTexto() %></dt>
		<dd>
			(<%=cita.getUsuario() %>): (<%=cita.getInicio() %>
			=>
			<%=cita.getFin() %>)
		</dd>
	</dl>
	<% } else { %>
		<p>No se ha encontrado la cita</p>
	<% } %>

</body>
</html>