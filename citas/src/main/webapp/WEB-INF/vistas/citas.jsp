<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<c:forEach items="${citas}" var="c">
	<li>
		<a href="detalle?id=${c.id}">${c.texto}</a>: 
		${c.inicio} => ${c.fin}</li>
</c:forEach>
</ul>
</body>
</html>