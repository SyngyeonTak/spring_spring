<%@page contentType="text/html; charset=utf-8"%>
<%
	String msg = (String)application.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%=msg %>
</body>
</html>