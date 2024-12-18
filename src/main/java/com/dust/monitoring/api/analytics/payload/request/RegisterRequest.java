package com.dust.monitoring.api.analytics.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
