package org.example.shareconfig2.config;

import org.example.shareconfig2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
       this.passwordEncoder = passwordEncoder;
       this.customUserDetailsService = customUserDetailsService;
   }

   @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException{
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User u = (User) customUserDetailsService.loadUserByUsername(username);

        if(passwordEncoder.matches(password, u.getPassword())){
            return new UsernamePasswordAuthenticationToken(username, null, u.getAuthorities());
        }
        else{
            throw new BadCredentialsException("Invalid username or password");
        }
   }



    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType
                .equals(UsernamePasswordAuthenticationToken.class);
    }
}
