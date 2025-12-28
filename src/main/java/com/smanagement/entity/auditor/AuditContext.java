package com.smanagement.entity.auditor;

import com.smanagement.entity.School;

public final class AuditContext {
    private static final ThreadLocal<String> CURRENT_AUDITOR   = new ThreadLocal<String>();

    private AuditContext(){}

    public static String getCurrentAuditor() {
        return CURRENT_AUDITOR.get();
    }
    public static void setCurrentAuditor(String userName) {
        CURRENT_AUDITOR.set(userName);
    }
    public static void clear() {
        CURRENT_AUDITOR.remove();
    }
}
