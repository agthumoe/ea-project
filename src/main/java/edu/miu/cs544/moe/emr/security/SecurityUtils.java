package edu.miu.cs544.moe.emr.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static String getPrinciple() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return "anonymousUser";
    }
}
