package com.dust.monitoring.api.analytics.service;

import com.dust.monitoring.api.analytics.entity.Users;
import com.dust.monitoring.api.analytics.payload.request.LoginRequest;
import com.dust.monitoring.api.analytics.payload.request.RegisterRequest;
import com.dust.monitoring.api.analytics.payload.response.LoginResponse;
import com.dust.monitoring.api.analytics.repository.UserRepository;
import com.dust.monitoring.api.analytics.utils.JwtUtils;
import com.dust.monitoring.api.analytics.utils.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginResponse authenticate(LoginRequest loginRequest, HttpServletRequest request) {

//        User user = userRepository.findUserByEmailOrUsername(loginRequest.getUsername(), loginRequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("Invalid username!"));
        ObjectMapper mapper = UtilityService.getMapper();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = this.authenticationProvider.authenticate(token);
        log.debug("Logging in with [{}]", authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        Users user = mapper.convertValue(authentication.getPrincipal(), Users.class);


        return new LoginResponse(jwtUtils.createToken(user, true)
                , jwtUtils.createToken(user, false), System.currentTimeMillis(),
                System.currentTimeMillis() + jwtUtils.getJwtExpirationMs());
    }

    public void register(RegisterRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        if (userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())) {
            throw new RuntimeException("User already exist!");
        }

        Users users = mapper.convertValue(request, Users.class);
        users.setRoles("User");
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        userRepository.save(users);

    }

}
