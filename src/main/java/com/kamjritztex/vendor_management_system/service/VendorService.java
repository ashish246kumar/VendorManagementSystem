package com.kamjritztex.vendor_management_system.service;

import com.kamjritztex.vendor_management_system.dto.ComplianceCheckRequest;
import com.kamjritztex.vendor_management_system.dto.VendorRequest;
import com.kamjritztex.vendor_management_system.dto.VendorResponse;

public interface VendorService {
    VendorResponse initiateOnboarding(VendorRequest vendorRequest);
    VendorResponse evaluateVendor(String vendorId);
    VendorResponse performComplianceCheck(String vendorId, ComplianceCheckRequest complianceCheckRequest);
    VendorResponse submitDocuments(String vendorId,boolean submited);
    VendorResponse integrateVendor(String vendorId);
}
