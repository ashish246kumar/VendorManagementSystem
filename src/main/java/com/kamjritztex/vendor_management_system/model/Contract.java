package com.kamjritztex.vendor_management_system.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {
    @Id
    private String contract_Id;
    private String vendorId;
    private List<String> termAndCondition;
    private String contractTitle;
    private ContractStatus contractStatus;
    private LocalDateTime start_Date;
    private LocalDateTime end_Date;

}
