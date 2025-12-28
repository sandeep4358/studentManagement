package com.smanagement.entity.auditor;

import org.springframework.data.domain.AuditorAware;

public class SimpleAuditorAware implements AuditorAware<String> {
    @Override
    public java.util.Optional<String> getCurrentAuditor() {
        String user = AuditContext.getCurrentAuditor();
        // In a real application, you would fetch the currently logged-in user.
        // For simplicity, we return a fixed username here.
        return java.util.Optional.ofNullable(user).or(()-> java.util.Optional.of("system"));
    }
}
