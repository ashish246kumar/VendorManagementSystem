package com.kamjritztex.vendor_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceMetricDto {
    private String vendorId;

    private double onTimeDeliveryRate;

    private double qualityRating;
    private boolean contractCompliance;

    private double responseTime;
    private String paymentTerms;

    private double price;

    private int priceChangeFrequency;

    private String vendorFinancialStability;

    private boolean isCompetitive;
    private double onTimeDeliveryWeight;
    private double qualityWeight;
    private double complianceWeight;
    private double responseTimeWeight;
    private double priceWeight;
    private double competitivenessWeight;
    private double overallScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
