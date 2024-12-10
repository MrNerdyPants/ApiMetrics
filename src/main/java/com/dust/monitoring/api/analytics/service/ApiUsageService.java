package com.dust.monitoring.api.analytics.service;

import com.dust.monitoring.api.analytics.entity.ApiUsage;
import com.dust.monitoring.api.analytics.repository.ApiUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ApiUsageService {

    @Autowired
    private ApiUsageRepository apiUsageRepository;

    public void logApiUsage(String userId, String apiEndpoint) {
        // Check if an entry exists for the given user and endpoint
        ApiUsage record = apiUsageRepository.findByUserIdAndApiEndpoint(userId, apiEndpoint);
        if (record != null) {
            record.setUsageCount(record.getUsageCount() + 1);
            record.setLastAccessed(Date.from(Instant.now()));
            apiUsageRepository.save(record);
        } else {
            // Create a new entry if it doesn't exist
            ApiUsage newRecord = new ApiUsage();
            newRecord.setUserId(userId);
            newRecord.setApiEndpoint(apiEndpoint);
            newRecord.setUsageCount(1L);
            newRecord.setLastAccessed(Date.from(Instant.now()));
            apiUsageRepository.save(newRecord);
        }
    }
}
