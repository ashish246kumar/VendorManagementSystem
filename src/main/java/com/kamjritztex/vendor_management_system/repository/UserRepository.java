package com.kamjritztex.vendor_management_system.repository;

import com.kamjritztex.vendor_management_system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
