package com.mitrian.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class InternalsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (checkRequestPathAllowed(req.getRequestURI())) {
			chain.doFilter(request, response);
		} else {
			resp.setStatus(404);
			resp.sendRedirect(req.getContextPath());
		}
	}

	private boolean checkRequestPathAllowed(String requestPath) {
		return !requestPath.endsWith(".jsp") && !requestPath.endsWith("/check");
	}
}
