package com.mitrian.servlet;

import com.mitrian.check.HitChecker;
import com.mitrian.dto.CheckResultDTO;
import com.mitrian.dto.PointCheckDTO;
import com.mitrian.exception.ValidationException;
import com.mitrian.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mitrian.util.WebUtils.forwardErrorPage;

@RequiredArgsConstructor
public class AreaCheckServlet extends HttpServlet {

	private final Validator<PointCheckDTO> validator;
	private final HitChecker hitChecker;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("x") == null || req.getParameter("y") == null
				|| req.getParameter("r") == null) {
			forwardErrorPage(req, resp, 400, "Not all required parameters are present");
			return;
		}

		Integer x = null;
		try {
			x = Integer.parseInt(req.getParameter("x"));
		} catch (NumberFormatException e) {
			forwardErrorPage(req, resp, 400, "X parameter should be an integer value");
			return;
		}

		Float y = null;
		try {
			y = Float.parseFloat(req.getParameter("y"));
		} catch (NumberFormatException e) {
			forwardErrorPage(req, resp, 400, "Y parameter should be a float value");
			return;
		}

		Float r = null;
		try {
			r = Float.parseFloat(req.getParameter("r"));
		} catch (NumberFormatException e) {
			forwardErrorPage(req, resp, 400, "R parameter should be a float value");
			return;
		}

		PointCheckDTO dto = new PointCheckDTO(x, y, r);
		try {
			validator.validate(dto);
		} catch (ValidationException e) {
			forwardErrorPage(req, resp, 400, e.getMessage());
			return;
		}

		boolean checkResult = hitChecker.check(dto);
		var checkResultDTO = new CheckResultDTO(x, y, r, checkResult, checkResult ? "succeed" : "missed");
		req.setAttribute("result", checkResultDTO);

		List<CheckResultDTO> results = getServletContext().getAttribute("results") == null
				? new ArrayList<>()
				: (List<CheckResultDTO>) getServletContext().getAttribute("results");
		results.add(checkResultDTO);
		getServletContext().setAttribute("results", results);

		getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
	}
}
