package com.projects.chatApp.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(@NonNull HttpServletRequest request,
                       @NonNull HttpServletResponse response,
                       @Nullable Authentication authentication) {
        SecurityContextHolder.clearContext();
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
    }
}
