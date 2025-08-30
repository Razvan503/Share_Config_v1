package org.example.shareconfig2.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.shareconfig2.Jwt.JwtService;
import org.example.shareconfig2.models.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {
    private final JpaTokenRepository jpaTokenRepository;
    private final userRepository userRepository;
    private final JwtService jwtService;

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository, userRepository userRepository, JwtService jwtService) {
        this.jpaTokenRepository = jpaTokenRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN","_csrf",uuid);
    }

    public void saveToken(CsrfToken csrfToken,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        String identifier = httpServletRequest.getHeader("X-CSRF-TOKEN");

        Optional<User> existingToken = jpaTokenRepository.findByToken(identifier);

        if (existingToken.isPresent()) {
            User user = existingToken.get();
            if(csrfToken == null){
                user.setToken("palceholder");
                jpaTokenRepository.save(user);
            }
            else{
                user.setToken(csrfToken.getToken());
                jpaTokenRepository.save(user);
            }
        } else {
            String Fulljwttoken = httpServletRequest.getHeader("Cookie");
            String  jwttoken = Fulljwttoken.substring("jwt=".length());
            String username = jwtService.extractUsername(jwttoken);

            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                User addUserToken = user.get();
                addUserToken.setToken(csrfToken.getToken());
                userRepository.save(addUserToken);
            }
        }

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        String identifier = httpServletRequest.getHeader("X-CSRF-TOKEN");
        Optional<User> existingToken = jpaTokenRepository.findByToken(identifier);
        if (existingToken.isPresent()) {
            String jwttoken = httpServletRequest.getHeader("Cookie");
            String Jwttoken = jwttoken.substring("jwt=".length());
            String username = jwtService.extractUsername(Jwttoken);
            Optional<User> user = userRepository.findByUsername(username);
            User userToken = user.get();
            String token =  userToken.getToken();
            return new DefaultCsrfToken("X-CSRF-TOKEN","_csrf",token);
        }
        return null;
    }
}
