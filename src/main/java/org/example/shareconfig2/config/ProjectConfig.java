package org.example.shareconfig2.config;

import org.example.shareconfig2.Jwt.JwtService;
import org.example.shareconfig2.Jwt.JwtValidationFilter;
import org.example.shareconfig2.repository.CustomCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class ProjectConfig {

    private final CustomCsrfTokenRepository csrfTokenRepository;
    private final AuthenticationProvider authenticationProvider;
    public ProjectConfig(CustomCsrfTokenRepository csrfTokenRepository, AuthenticationProvider authenticationProvider) {
        this.csrfTokenRepository = csrfTokenRepository;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtValidationFilter jwtValidationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(jwtValidationFilter, AuthenticationFilter.class);
        http.sessionManagement((session)->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.csrf( c-> {
                    c.ignoringRequestMatchers("/api/login");
                    c.ignoringRequestMatchers("/api/register");
                    c.ignoringRequestMatchers("ks/admin/config","ks/admin/user","ks/admin/killswitch");
                    c.csrfTokenRepository(csrfTokenRepository);
                    c.csrfTokenRequestHandler( new CsrfEndpointRequestHandler()
                    );
                }
        );


        http.authenticationProvider(authenticationProvider);
        http.cors( c ->{
            CorsConfigurationSource source = request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(List.of("https://shareconfig.onrender.com"));
            corsConfiguration.setAllowedMethods(List.of("GET","POST","DELETE"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            return corsConfiguration;
        };
            c.configurationSource(source);
        });

        http.authorizeHttpRequests( c ->
                c.requestMatchers("/api/login", "/api/register","/home","/hello","/signup","/signin","/","/images/favicon.png","/error").permitAll()
                       anyRequest().authenticated()
        );

        return http.build();
    }
}
