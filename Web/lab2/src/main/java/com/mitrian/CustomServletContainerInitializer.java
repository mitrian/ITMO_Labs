package com.mitrian;

import com.mitrian.check.HitChecker;
import com.mitrian.check.HitCheckerImpl;
import com.mitrian.dto.PointCheckDTO;
import com.mitrian.servlet.AreaCheckServlet;
import com.mitrian.servlet.ControllerServlet;
import com.mitrian.validation.CoordinatesRequestValidator;
import com.mitrian.validation.Validator;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

public class CustomServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		ctx.addJspFile("indexJsp", "/pages/index.jsp")
						.addMapping("/index.jsp");

		ctx.addJspFile("resultJsp", "/pages/result.jsp")
				.addMapping("/result.jsp");


		ctx.addJspFile("errorJsp", "/pages/error.jsp")
				.addMapping("/error.jsp");

		Validator<PointCheckDTO> validator = new CoordinatesRequestValidator();
		HitChecker hitChecker = new HitCheckerImpl();

		AreaCheckServlet areaCheckServlet = new AreaCheckServlet(validator, hitChecker);
		ctx.addServlet("areaCheckServlet", areaCheckServlet)
				.addMapping("/check");


		ControllerServlet controllerServlet = new ControllerServlet();
		ctx.addServlet("controllerServlet", controllerServlet)
				.addMapping("/*");
	}
}
