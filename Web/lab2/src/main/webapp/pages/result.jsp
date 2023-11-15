<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Result page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
	<header>
		<div class="title-container">
			Results
		</div>
	</header>
	<div class="result-container">
		<table class="result-table">
			<thead>
			<tr>
				<td>X</td>
				<td>Y</td>
				<td>R</td>
				<td>Result</td>
			</tr>
			</thead>
			<tbody id="result">
			<tr>
				<td>${ result.x }</td>
				<td>${ result.y }</td>
				<td>${ result.r }</td>
				<td>${ result.resultString }</td>
			</tr>
			</tbody>
		</table>
	</div>


	<p><a href="<%= request.getContextPath() %>">Back to main page</a></p>
</body>
</html>