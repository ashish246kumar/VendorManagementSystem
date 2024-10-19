package com.kamjritztex.vendor_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "Interaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Interaction {
    @Id
    private String id;
    private String vendorId;
    private String description;
    private String createdBy;
    private boolean isResolved;
    private CommunicationChannel communicationChannel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
