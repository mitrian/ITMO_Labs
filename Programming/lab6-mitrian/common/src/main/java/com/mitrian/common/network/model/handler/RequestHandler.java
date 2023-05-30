package com.mitrian.common.network.model.handler;

import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.model.response.Response;

/**
 * Request handling functional interface
 *
 */
@FunctionalInterface
public interface RequestHandler {
	/**
	 * Handling method
	 *
	 * @param request request that is being handled
	 * @return response instance
	 */
	Response handle(Request request);
}
