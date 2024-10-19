package com.kamjritztex.vendor_management_system.dto;

import com.kamjritztex.vendor_management_system.model.CommunicationChannel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InteractionDto {
    private String vendorId;
    private String description;
    private String createdBy;
    private boolean isResolved;
    private CommunicationChannel communicationChannel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
