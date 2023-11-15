<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Index</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>

	<%@include file="components/header.jsp"%>

	<table class="general-container">
		<tr>
			<th class="form-container">
				<%@include file="components/form.jsp"%>
			</th>
			<th class="canvas-container">
				<%@include file="components/canvas.jsp"%>
			</th>
			<th class="result-container">
				<%@include file="components/result-table.jsp"%>
			</th>
		</tr>







	</table>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script type="module" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
