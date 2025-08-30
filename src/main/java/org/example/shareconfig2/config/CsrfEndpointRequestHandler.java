package org.example.shareconfig2.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.util.function.Supplier;

public class CsrfEndpointRequestHandler extends CsrfTokenRequestAttributeHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> deferredCsrfToken) {
        if ("GET".equalsIgnoreCase(request.getMethod()) && "/hello".equals(request.getRequestURI())) {
            deferredCsrfToken.get(); // force creation
        }
        super.handle(request, response, deferredCsrfToken);
    }
}
