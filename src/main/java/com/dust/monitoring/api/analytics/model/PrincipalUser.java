package com.dust.monitoring.api.analytics.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PrincipalUser extends User {
    public PrincipalUser(String username, String email, String password, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired, boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String roles, Date createdAt, Date updatedAt) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private String email;
    private String firstName;
    private String lastName;
    private String roles;
    private Date createdAt;
    private Date updatedAt;

}
