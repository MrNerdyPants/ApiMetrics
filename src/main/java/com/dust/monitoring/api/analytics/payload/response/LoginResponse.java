package com.dust.monitoring.api.analytics.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private long issuedAt;
    private long expiry;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
