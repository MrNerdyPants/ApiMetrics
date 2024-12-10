package com.dust.monitoring.api.analytics.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUsageMetricService {
    private final MeterRegistry meterRegistry;

    @Autowired
    public ApiUsageMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementApiUsage(String userId, String apiEndpoint) {
        meterRegistry.counter("api.usage", "user", userId, "endpoint", apiEndpoint).increment();
    }
}

