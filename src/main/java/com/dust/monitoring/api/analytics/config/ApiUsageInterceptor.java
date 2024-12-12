package com.dust.monitoring.api.analytics.config;

import com.dust.monitoring.api.analytics.entity.Users;
import com.dust.monitoring.api.analytics.enums.AllowedUrl;
import com.dust.monitoring.api.analytics.utils.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiUsageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ObjectMapper mapper = UtilityService.getMapper();

        if (AllowedUrl.uriExist(request.getRequestURI())) {
            return true;
        }

        // Extract user identifier (e.g., from JWT or custom header)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = mapper.convertValue(authentication.getPrincipal(), Users.class);
        String userId = user.getUsername();
//                request.getHeader(user.getUsername()); // Replace with your actual logic

        // Extract API endpoint
        String apiEndpoint = request.getRequestURI();

        // Log or increment usage count
        System.out.println("User: " + userId + ", API: " + apiEndpoint);

        // Optionally, forward this data to a monitoring service
        return true;
    }
}
