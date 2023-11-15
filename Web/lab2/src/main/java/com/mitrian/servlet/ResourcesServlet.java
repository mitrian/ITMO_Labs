package com.mitrian.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/resources/*")
public class ResourcesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String[] res = req.getRequestURI().split("/resources");
		if (res.length < 2) {
			resp.setStatus(404);
			resp.getWriter().println("Resource not found");
			return;
		}

		String resource = res[res.length - 1];

		if (resource.endsWith(".css"))
			resp.setContentType("text/css");
		else if (resource.endsWith(".js"))
			resp.setContentType("text/javascript");

		try(var asset = getClass().getClassLoader().getResourceAsStream(resource)) {
			if (asset != null)
				asset.transferTo(resp.getOutputStream());
		}
	}
}
