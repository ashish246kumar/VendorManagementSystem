package com.kamjritztex.vendor_management_system.controller;


import com.kamjritztex.vendor_management_system.dto.PerformanceMetricDto;
import com.kamjritztex.vendor_management_system.dto.PerformanceMetricsResponse;
import com.kamjritztex.vendor_management_system.service.PerformanceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance-metrics")
public class PerformanceMetricsController {

    @Autowired
    private PerformanceMetricsService performanceMetricsService;
    @GetMapping
    public ResponseEntity<List<PerformanceMetricsResponse>> getAllMetrics() {
        List<PerformanceMetricsResponse> metrics = performanceMetricsService.getAllMetrics();
        return ResponseEntity.ok(metrics);
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<PerformanceMetricsResponse> getMetricsByVendorId(@PathVariable String vendorId) {
        PerformanceMetricsResponse metrics = performanceMetricsService.getMetricsByVendorId(vendorId);
        return ResponseEntity.ok(metrics);
    }
    @PostMapping("/calculate")
    public ResponseEntity<String> createOrUpdateMetrics(@RequestBody PerformanceMetricDto metrics) {
        String savedMetrics = performanceMetricsService.createOrUpdateMetrics(metrics);
        return ResponseEntity.ok(savedMetrics);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMetrics(@PathVariable String id) {
      String message=  performanceMetricsService.deleteMetrics(id);
        return ResponseEntity.ok(message);
    }

}
