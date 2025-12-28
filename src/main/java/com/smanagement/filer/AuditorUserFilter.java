package com.smanagement.filer;

import com.smanagement.entity.auditor.AuditContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Filter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuditorUserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String userId = request.getHeader("X-User-Id");
            if (userId != null && !userId.isEmpty()) {
                AuditContext.setCurrentAuditor(userId);
            }else {
                AuditContext.setCurrentAuditor("system");
            }
            filterChain.doFilter(request, response);
        } finally {
            AuditContext.clear(); // always clear to avoid Leakage across threads
        }
    }
}
