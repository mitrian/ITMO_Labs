package com.mitrian.lab.common.network.handler;

import com.mitrian.lab.common.network.model.Request;
import com.mitrian.lab.common.network.model.Response;

@FunctionalInterface
public interface RequestHandler<REQ extends Request, RES extends Response> {
	RES handle(REQ request);
}
