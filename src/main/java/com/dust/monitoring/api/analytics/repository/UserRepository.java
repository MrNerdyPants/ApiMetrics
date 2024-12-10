package com.dust.monitoring.api.analytics.repository;

import com.dust.monitoring.api.analytics.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);


    boolean existsByEmailOrUsername(String email, String userName);

    Optional<Users> findUserByEmailOrUsername(String email, String userName);
}
