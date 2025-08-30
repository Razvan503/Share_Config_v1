package org.example.shareconfig2.Jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtValidationFilter implements Filter {

    private JwtService jwtService;

    @Autowired
    public JwtValidationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String uri = httpRequest.getRequestURI();
        if(uri.equals("/favicon.ico")){
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if(uri.equals("/.well-known/appspecific/com.chrome.devtools.json")){
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
       if(uri.equals("/signin") || uri.equals("/signup") || uri.equals("/api/login") || uri.equals("/api/register") || uri.equals("/") || uri.equals("/images/favicon.png") || uri.equals("/error")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String jwt = httpRequest.getHeader("Cookie");
        String token = jwt.substring("jwt=".length());
        if(token == null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            String username =  jwtService.extractUsername(token);
            if(username == null){
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            else{
                List<String> roles = jwtService.extractRoles(token);
                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("Authenticated? " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }
}
