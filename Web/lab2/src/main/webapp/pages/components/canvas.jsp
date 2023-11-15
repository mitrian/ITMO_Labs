<%@ page import="com.mitrian.dto.CheckResultDTO" %>
<%@ page import="java.util.List" %>
<div class="canvas-container">
	<canvas id="area" width="300px" height="300px"></canvas>
</div>

<script>
	let calculatedPoints = []

	function loadCalculatedPoints() {
		let pointsToBeLoaded = []
		<%
			if (config.getServletContext().getAttribute("results") != null)
			{
				for (CheckResultDTO result : (List<CheckResultDTO>) config.getServletContext().getAttribute("results"))
				{
		%>
			pointsToBeLoaded.push({x: <%= result.x %>, y: <%= result.y %>, hit: <%= result.result %>})
		<%
				}
			}
		%>

		calculatedPoints = pointsToBeLoaded
	}

	loadCalculatedPoints()
</script>