package com.kamjritztex.vendor_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "vendor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Vendor {
    @Id
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
