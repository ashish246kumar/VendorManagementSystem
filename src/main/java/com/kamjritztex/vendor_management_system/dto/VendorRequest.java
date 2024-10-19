package com.kamjritztex.vendor_management_system.dto;

import com.kamjritztex.vendor_management_system.model.OnboardingStage;
import com.kamjritztex.vendor_management_system.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorRequest {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Status status;
    private String vendorType;
    private OnboardingStage onboardingStage;
    private boolean complianceApproved;
    private boolean documentsSubmitted;
    private boolean legalDocumentsSigned;
    private boolean integratedIntoSystem;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
