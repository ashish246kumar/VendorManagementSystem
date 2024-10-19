package com.kamjritztex.vendor_management_system.service;

import com.kamjritztex.vendor_management_system.dto.PerformanceMetricDto;
import com.kamjritztex.vendor_management_system.dto.PerformanceMetricsResponse;
import com.kamjritztex.vendor_management_system.model.PerformanceMetrics;

import java.util.List;

public interface PerformanceMetricsService {
    List<PerformanceMetricsResponse> getAllMetrics();
    PerformanceMetricsResponse getMetricsByVendorId(String vendorId);
    String createOrUpdateMetrics(PerformanceMetricDto metrics);
    String deleteMetrics(String id);
}
