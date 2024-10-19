package com.kamjritztex.vendor_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceMetricsResponse {
    private String vendorId;
    private double onTimeDeliveryWeight;
    private double qualityWeight;
    private double complianceWeight;
    private double responseTimeWeight;
    private double priceWeight;
    private double competitivenessWeight;
    private double overallScore;
    private double onTimeDeliveryRate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
