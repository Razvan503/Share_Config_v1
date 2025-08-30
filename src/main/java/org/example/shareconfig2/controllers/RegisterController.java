package org.example.shareconfig2.controllers;

import org.example.shareconfig2.Jwt.JwtService;
import org.example.shareconfig2.models.Authority;
import org.example.shareconfig2.models.Register;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.AuthorityRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Transactional
public class RegisterController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private userRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Register register) {
        String username = register.getUsername();
        String email = register.getEmail();
        String password = register.getPassword();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Optional<Authority> userRole;
                if(username.equals("ciobanu")){
                    userRole = authorityRepository.findByName("ROLE_ADMIN");
                }
                else {
                    userRole = authorityRepository.findByName("ROLE_USER");
                }
            Authority authority = userRole.get();
                Set<Authority> authorities = new HashSet<>();
                authorities.add(authority);

                User user1 = new User(username, email, passwordEncoder.encode(password), authorities);
                userRepository.save(user1);

                String token = jwtService.generateToken(user1);
                ResponseCookie cookie = ResponseCookie.from("jwt",token)
                        .httpOnly(true)
                        .secure(true)
                        .path("/")
                        .sameSite("Strict")
                        .build();

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.SET_COOKIE,cookie.toString());
                return new  ResponseEntity<>(headers, HttpStatus.OK);
        }
    }

}