package pl.harpi.samples.j2ee.demo.person.rest;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

public class RestUrlBuilder {
    private RestUrlBuilder() {
    }

    public static String buildUrl(HttpServletRequest request, String[] fragments, Object... arguments) {
        StringBuilder url = new StringBuilder(request.isSecure() ? "https" : "http");
        url.append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath()).append(request.getServletPath());

        for (String f : fragments) {
            url.append("/").append(f);
        }

        url = new StringBuilder(MessageFormat.format(url.toString(), arguments));

        return url.toString();
    }
}