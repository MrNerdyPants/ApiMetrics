package com.dust.monitoring.api.analytics.repository;

import com.dust.monitoring.api.analytics.entity.ApiUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiUsageRepository extends JpaRepository<ApiUsage, Long> {

    ApiUsage findByUserIdAndApiEndpoint(String userId, String endpoint);

}
