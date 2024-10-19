package com.kamjritztex.vendor_management_system.serviceImpl;

import com.kamjritztex.vendor_management_system.dto.PerformanceMetricDto;
import com.kamjritztex.vendor_management_system.dto.PerformanceMetricsResponse;
import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.model.PerformanceMetrics;
import com.kamjritztex.vendor_management_system.repository.PerformanceMetricsRepository;
import com.kamjritztex.vendor_management_system.service.PerformanceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceMetricsServiceImpl implements PerformanceMetricsService {

    @Autowired
    private PerformanceMetricsRepository performanceMetricsRepository;

    @Override
    public List<PerformanceMetricsResponse> getAllMetrics() {
        List<PerformanceMetrics> performanceMetrics= performanceMetricsRepository.findAll();
        return performanceMetrics.stream()
                .map(metric -> PerformanceMetricsResponse.builder()
                        .vendorId(metric.getVendorId()) // Adjust based on actual field name
                        .onTimeDeliveryWeight(metric.getOnTimeDeliveryWeight()) // Adjust based on actual field name
                        .qualityWeight(metric.getQualityWeight()) // Adjust based on actual field name
                        .complianceWeight(metric.getComplianceWeight()) // Adjust based on actual field name
                        .responseTimeWeight(metric.getResponseTimeWeight()) // Adjust based on actual field name
                        .priceWeight(metric.getPriceWeight()) // Adjust based on actual field name
                        .competitivenessWeight(metric.getCompetitivenessWeight()) // Adjust based on actual field name
                        .overallScore(metric.getOverallScore()) // Adjust based on actual field name
                        .onTimeDeliveryRate(metric.getOnTimeDeliveryRate()) // Adjust based on actual field name
                        .createdAt(metric.getCreatedAt()) // Adjust based on actual field name
                        .updatedAt(metric.getUpdatedAt()) // Adjust based on actual field name
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public PerformanceMetricsResponse getMetricsByVendorId(String vendorId) {
       PerformanceMetrics performanceMetrics= performanceMetricsRepository.findByVendorId(vendorId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new CustomException("Metrics not found for vendor: " + vendorId, HttpStatus.NOT_FOUND));
        PerformanceMetricsResponse performanceMetricsResponse=PerformanceMetricsResponse.builder()
                .vendorId(performanceMetrics.getVendorId())
                .overallScore(performanceMetrics.getOverallScore())
                .onTimeDeliveryWeight(performanceMetrics.getOnTimeDeliveryWeight())
                .complianceWeight(performanceMetrics.getComplianceWeight())
                .qualityWeight(performanceMetrics.getQualityWeight())
                .onTimeDeliveryRate(performanceMetrics.getOnTimeDeliveryRate())
                .createdAt(performanceMetrics.getCreatedAt())
                .updatedAt(performanceMetrics.getUpdatedAt())
                .build();
        return performanceMetricsResponse;

    }

    @Override
    public String createOrUpdateMetrics(PerformanceMetricDto metrics) {
        double score=calculateScore(metrics);
       PerformanceMetrics performanceMetrics= PerformanceMetrics.builder()
               .vendorId(metrics.getVendorId())
               .onTimeDeliveryRate(metrics.getOnTimeDeliveryRate())
                .onTimeDeliveryWeight(metrics.getOnTimeDeliveryWeight())
                .contractCompliance(metrics.isContractCompliance())
                .responseTime(metrics.getResponseTime())
                .responseTimeWeight(metrics.getResponseTimeWeight())
                .price(metrics.getPrice())
                .priceWeight(metrics.getPriceWeight())
               .qualityRating(metrics.getQualityRating())
               .overallScore(score)
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
                .build();
        metrics.setCreatedAt(LocalDateTime.now());
        metrics.setUpdatedAt(LocalDateTime.now());
        performanceMetricsRepository.save(performanceMetrics);
       return "PerformanceMetricsCreatedOrUpdatedSucessFully";
    }
    private double calculateScore(PerformanceMetricDto metrics) {
        double overallScore =
                (metrics.getOnTimeDeliveryRate() * metrics.getOnTimeDeliveryWeight()) +
                        (metrics.getQualityRating() * metrics.getQualityWeight()) +
                        (metrics.isContractCompliance() ? 1 : 0 * metrics.getComplianceWeight()) +
                        (1 / metrics.getResponseTime() * metrics.getResponseTimeWeight()) +
                        (1 / metrics.getPrice() * metrics.getPriceWeight()) +
                        (metrics.isCompetitive() ? 1 : 0 * metrics.getCompetitivenessWeight());

       return overallScore;
    }
    @Override
    public String deleteMetrics(String id) {
        performanceMetricsRepository.deleteById(id);
        return "performanceMetricsDeletedSucessfully";
    }
}
