package com.mitrian.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.mitrian.util.WebUtils.forwardErrorPage;

public class ControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("x") != null && req.getParameter("y") != null
				&& req.getParameter("r") != null)
			getServletContext().getRequestDispatcher("/check").forward(req, resp);
		else
			forwardErrorPage(req, resp, 400, "Invalid POST request parameters");
	}
}
