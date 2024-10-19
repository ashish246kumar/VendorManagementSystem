package com.kamjritztex.vendor_management_system.dto;

import com.kamjritztex.vendor_management_system.model.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {
    private String vendorId;
    private List<String> termAndCondition;
    private String contractTitle;
    private ContractStatus contractStatus;
    private LocalDateTime start_Date;
    private LocalDateTime end_Date;
}
