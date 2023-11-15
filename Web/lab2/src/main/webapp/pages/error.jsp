<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Error</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
	<header class="error-header">
		Error
	</header>
	<h2 class="error-message">${error.message}</h2>
	<p><a href="<%= request.getContextPath() %>">Back to main page</a></p>
</body>
</html>