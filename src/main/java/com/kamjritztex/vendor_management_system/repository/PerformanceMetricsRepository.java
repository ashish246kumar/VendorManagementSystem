package com.kamjritztex.vendor_management_system.repository;

import com.kamjritztex.vendor_management_system.model.PerformanceMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PerformanceMetricsRepository extends MongoRepository<PerformanceMetrics, String> {
    List<PerformanceMetrics> findByVendorId(String vendorId);


}
