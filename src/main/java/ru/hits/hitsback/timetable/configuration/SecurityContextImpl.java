package ru.hits.hitsback.timetable.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

public class SecurityContextImpl implements SecurityContextRepository  {

    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            context = createEmptyContext();
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        // Not implemented
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }

    private SecurityContext createEmptyContext() {
        return SecurityContextHolder.createEmptyContext();
    }
}
