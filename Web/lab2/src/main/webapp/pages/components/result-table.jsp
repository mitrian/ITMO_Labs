<%@ page import="java.util.List" %>
<%@ page import="com.mitrian.dto.CheckResultDTO" %>

<div class="results-container">
	<div class="table-wrapper">
		<table>
			<thead>
				<tr>
					<td>X</td>
					<td>Y</td>
					<td>R</td>
					<td>Result</td>
				</tr>
			</thead>
			<tbody>
				<%
					if (config.getServletContext().getAttribute("results") != null)
					{
						for (CheckResultDTO result : (List<CheckResultDTO>) config.getServletContext().getAttribute("results"))
						{
				%>
					<tr>
						<td><%= result.getX() %></td>
						<td><%= result.getY() %></td>
						<td><%= result.getR() %></td>
						<td><%= result.getResultString() %></td>
					</tr>
				<%      }
					}
				%>
			</tbody>
		</table>
	</div>
</div>
