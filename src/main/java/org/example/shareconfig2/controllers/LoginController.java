package org.example.shareconfig2.controllers;

import org.apache.catalina.connector.Response;
import org.example.shareconfig2.Jwt.JwtService;
import org.example.shareconfig2.config.CustomUserDetailsService;
import org.example.shareconfig2.models.Login;
import org.example.shareconfig2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Login login){
        String username=login.getUsername();
        String password=login.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) userDetailsService.loadUserByUsername(username);
        String token = jwtService.generateToken(user);
        HttpHeaders headers = new HttpHeaders();

        ResponseCookie cookie = ResponseCookie.from("jwt",token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .build();

        headers.add(HttpHeaders.SET_COOKIE,cookie.toString());

       return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
