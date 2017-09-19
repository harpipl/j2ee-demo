package pl.harpi.samples.j2ee.demo.base.service.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

public abstract class BaseResource {
    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    protected HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    protected HttpServletResponse getHttpResponse() {
        return httpResponse;
    }
}