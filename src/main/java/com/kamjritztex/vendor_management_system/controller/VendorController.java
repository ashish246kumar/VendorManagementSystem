package com.kamjritztex.vendor_management_system.controller;


import com.kamjritztex.vendor_management_system.dto.ComplianceCheckRequest;
import com.kamjritztex.vendor_management_system.dto.VendorRequest;
import com.kamjritztex.vendor_management_system.dto.VendorResponse;
import com.kamjritztex.vendor_management_system.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VendorController {

    @Autowired
    private VendorService vendorService;
    @PostMapping("/vendor/onboard")
    public ResponseEntity<VendorResponse> onboardVendor(@RequestBody VendorRequest vendor) {
        VendorResponse onboardedVendor = vendorService.initiateOnboarding(vendor);
        return ResponseEntity.ok(onboardedVendor);
    }

    @PostMapping("/vendor/evaluate/{vendorId}")
    public ResponseEntity<?> evaluateVendor(@PathVariable String vendorId){
        VendorResponse evaluatedVendor = vendorService.evaluateVendor(vendorId);
        return ResponseEntity.ok(evaluatedVendor);
    }
    @PostMapping("/vendor/compliance-check/{vendorId}")
    public ResponseEntity<VendorResponse> performComplianceCheck(
            @PathVariable String vendorId,
            @RequestBody ComplianceCheckRequest complianceCheckRequest) {
        VendorResponse checkedVendor = vendorService.performComplianceCheck(vendorId,complianceCheckRequest);
        return ResponseEntity.ok(checkedVendor);
    }

    @PostMapping("/vendor/submit-documents/{vendorId}")
    public ResponseEntity<VendorResponse> submitDocuments(@PathVariable String vendorId, @RequestParam boolean submitted) {
        VendorResponse vendorWithDocs = vendorService.submitDocuments(vendorId, submitted);
        return ResponseEntity.ok(vendorWithDocs);
    }
    @PostMapping("/v1/vendor/integrate/{vendorId}")
    public ResponseEntity<VendorResponse> integrateVendor(@PathVariable String vendorId) {
        VendorResponse integratedVendor = vendorService.integrateVendor(vendorId);
        return ResponseEntity.ok(integratedVendor);
    }




}
