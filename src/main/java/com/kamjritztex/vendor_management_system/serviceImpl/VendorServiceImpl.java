package com.kamjritztex.vendor_management_system.serviceImpl;

import com.kamjritztex.vendor_management_system.dto.ComplianceCheckRequest;
import com.kamjritztex.vendor_management_system.dto.VendorRequest;
import com.kamjritztex.vendor_management_system.dto.VendorResponse;
import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.model.OnboardingStage;
import com.kamjritztex.vendor_management_system.model.Status;
import com.kamjritztex.vendor_management_system.model.Vendor;
import com.kamjritztex.vendor_management_system.repository.VendorRepository;
import com.kamjritztex.vendor_management_system.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    @Override
    public VendorResponse initiateOnboarding(VendorRequest vendorRequest) {
   Optional <Vendor> isVendorExist=vendorRepository.findByName(vendorRequest.getName());
   if(isVendorExist.isPresent()){
       return new VendorResponse(isVendorExist.get().getId(),"VendorAllready Present");
   }
        Vendor vendor=Vendor.builder()
                .id(UUID.randomUUID().toString())
                .name(vendorRequest.getName())
                .email(vendorRequest.getEmail())
                .phone(vendorRequest.getPhone())
                .address(vendorRequest.getAddress())
                .createdAt(LocalDateTime.now())
                .status(Status.PENDING)
                .onboardingStage(OnboardingStage.IDENTIFICATION)
                .updatedAt(LocalDateTime.now())
                .vendorType(vendorRequest.getVendorType())
                .build();
         vendorRepository.save(vendor);
         return new VendorResponse(vendor.getId(),"VendorIntialOnboardingSucessFull");


    }

    @Override
    public VendorResponse evaluateVendor(String vendorId) {
        Vendor vendor = getVendorById(vendorId);
        validateStageTransition(vendor, OnboardingStage.EVALUATION);
        vendor.setOnboardingStage(OnboardingStage.EVALUATION);
        vendor.setUpdatedAt(LocalDateTime.now());
         vendorRepository.save(vendor);
        return new VendorResponse(vendor.getId(),"EvalutationOfVendorSucessfull");

    }

    @Override
    public VendorResponse performComplianceCheck(String vendorId, ComplianceCheckRequest complianceCheckRequest) {
        Vendor vendor = getVendorById(vendorId);
        validateStageTransition(vendor, OnboardingStage.COMPLIANCE_CHECK);
        vendor.setOnboardingStage(OnboardingStage.COMPLIANCE_CHECK);
        vendor.setComplianceApproved(complianceCheckRequest.isPassed());
        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);
        return new VendorResponse(vendor.getId(),"performCompilanceCheckSucessFull");
    }

    @Override
    public VendorResponse submitDocuments(String vendorId, boolean submited) {
        Vendor vendor = getVendorById(vendorId);
        validateStageTransition(vendor, OnboardingStage.DOCUMENTS_CREATION);
        vendor.setOnboardingStage(OnboardingStage.DOCUMENTS_CREATION);
        vendor.setDocumentsSubmitted(submited);
        vendor.setUpdatedAt(LocalDateTime.now());
         vendorRepository.save(vendor);
        return new VendorResponse(vendor.getId(),"submitDocumentSuccessFull");


    }

    @Override
    public VendorResponse integrateVendor(String vendorId) {
        Vendor vendor = getVendorById(vendorId);
        if (vendor.getOnboardingStage() != OnboardingStage.DOCUMENTS_CREATION || !vendor.isDocumentsSubmitted()) {
            throw new CustomException("Vendor must submit documents before integration.",HttpStatus.BAD_REQUEST);
        }
        vendor.setOnboardingStage(OnboardingStage.INTEGRATION);
        vendor.setIntegratedIntoSystem(true);
        vendor.setStatus(Status.APPROVED);
        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);
        return new VendorResponse(vendor.getId(),"vendorSucessfullyIntegrated");

    }
    private Vendor getVendorById(String vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new CustomException("Vendor not found", HttpStatus.NOT_FOUND));
    }
    private void validateStageTransition(Vendor vendor, OnboardingStage nextStage) {
        OnboardingStage currentStage = vendor.getOnboardingStage();
        if (currentStage.ordinal() > nextStage.ordinal()) {
            throw new CustomException("You are in the wrong Stage", HttpStatus.BAD_REQUEST);
        }
        if (currentStage == nextStage) {
            throw new CustomException("Vendor is alredy crossed this stage",HttpStatus.BAD_REQUEST);

        }

    }
    }
