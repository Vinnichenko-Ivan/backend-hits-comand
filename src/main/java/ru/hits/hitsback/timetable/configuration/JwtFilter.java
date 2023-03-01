package ru.hits.hitsback.timetable.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.service.JwtService;

import java.io.IOException;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        JwtAuthentication authentication = new JwtAuthentication();
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        try{
            Account account = jwtService.getAccountByToken(token);
            if(account != null) {
                if (!account.getAccepted()) {
                    throw new UnauthorizedException();
                }
//                ((HttpServletRequest) servletRequest).isUserInRole(account.getRoles().toString())
                authentication.setAuthenticated(true);
                authentication.setAccount(account);
                authentication.setFirstName(token);
                authentication.setUsername(account.getEmail());
                authentication.setRoles(account.getRoles() != null ? Set.of(account.getRoles()) : null);
            } else {
                throw new UnauthorizedException();
            }
        }
        catch (UnauthorizedException e){
            authentication.setAuthenticated(false);
            authentication.setAccount(null);
            authentication.setFirstName(null);
            authentication.setUsername(null);
            authentication.setRoles(null);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
