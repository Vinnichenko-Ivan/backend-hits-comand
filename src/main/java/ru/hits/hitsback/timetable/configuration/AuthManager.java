package ru.hits.hitsback.timetable.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.hits.hitsback.timetable.model.entity.Account;

@Component
@RequiredArgsConstructor
public class AuthManager implements AuthenticationManager {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
//    private BCryptProperties bCryptProperties;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String a = "";
        return null;
    }

    public Account getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (Account) authentication.getPrincipal();
        }
    }

}
