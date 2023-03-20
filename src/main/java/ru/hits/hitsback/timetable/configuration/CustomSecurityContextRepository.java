package ru.hits.hitsback.timetable.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;


public class CustomSecurityContextRepository implements SecurityContextRepository {
    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        return SecurityContextRepository.super.loadDeferredContext(request);
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        return SecurityContextHolder.getContext();
    }

    @Override
    public void saveContext(SecurityContext securityContext, HttpServletRequest request, HttpServletResponse response) {
        // do nothing, as the context is automatically propagated by the SecurityContextHolder
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return true;
    }
}
