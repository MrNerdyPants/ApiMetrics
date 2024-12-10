package com.dust.monitoring.api.analytics.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiUsageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extract user identifier (e.g., from JWT or custom header)
        String userId = request.getHeader("User-ID"); // Replace with your actual logic

        // Extract API endpoint
        String apiEndpoint = request.getRequestURI();

        // Log or increment usage count
        System.out.println("User: " + userId + ", API: " + apiEndpoint);

        // Optionally, forward this data to a monitoring service
        return true;
    }
}
