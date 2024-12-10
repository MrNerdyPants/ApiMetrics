package com.dust.monitoring.api.analytics.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String roles;
    private Date createdAt;
    private Date updatedAt;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
        this.createdAt = Date.from(Instant.now());
    }

    @PostPersist
    void postValue() {
        this.updatedAt = Date.from(Instant.now());
    }
}
