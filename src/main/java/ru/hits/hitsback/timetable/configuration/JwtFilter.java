package ru.hits.hitsback.timetable.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.hits.hitsback.timetable.exception.NotAcceptedException;
import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.service.JwtService;

import java.io.IOException;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtAuthentication authentication = new JwtAuthentication();
        String token = getTokenFromRequest(request);
        if (token != null) {
            try {
                Account account = jwtService.getAccountByToken(token);
                if (account != null) {
                    if (!account.getAccepted()) {
                        throw new NotAcceptedException();
                    }
                    authentication.setAuthenticated(true);
                    authentication.setAccount(account);
                    authentication.setFirstName(token);
                    authentication.setUsername(account.getEmail());
                    authentication.setRoles(account.getRole() != null ? Set.of(account.getRole()) : null);
                } else {
                    throw new UnauthorizedException();
                }
            } catch (UnauthorizedException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Un");
                return;
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
