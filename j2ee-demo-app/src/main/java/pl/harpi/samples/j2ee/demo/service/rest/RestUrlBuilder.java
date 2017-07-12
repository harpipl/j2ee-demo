package pl.harpi.samples.j2ee.demo.service.rest;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

public class RestUrlBuilder {
    public static String buildUrl(HttpServletRequest request, String fragments[], Object ... arguments) {
        String url = (request.isSecure()) ? "https" : "http";
        url += "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath();

        for (String f : fragments) {
            url += "/" + f;
        }

        url = MessageFormat.format(url, arguments);

        return url;
    }
}