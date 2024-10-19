package com.kamjritztex.vendor_management_system.repository;

import com.kamjritztex.vendor_management_system.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VendorRepository extends MongoRepository<Vendor,String> {
  Optional<Vendor> findByName(String name);
}
