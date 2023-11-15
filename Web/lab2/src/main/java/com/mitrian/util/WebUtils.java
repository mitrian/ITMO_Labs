package com.mitrian.util;

import com.mitrian.dto.ErrorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUtils {

	public static void forwardErrorPage(HttpServletRequest req, HttpServletResponse resp, int status, String message) throws ServletException, IOException {
		resp.setStatus(status);
		req.setAttribute("error", new ErrorDTO(message));
		req.getRequestDispatcher("/error.jsp").forward(req, resp);
	}

}
