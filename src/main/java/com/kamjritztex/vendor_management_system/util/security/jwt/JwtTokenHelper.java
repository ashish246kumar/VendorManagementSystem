package com.kamjritztex.vendor_management_system.util.security.jwt;

import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.util.security.SecretKey;

import java.util.Set;

public interface JwtTokenHelper {

    String generateJwtToken(SecretKey secretKey, String userName, Set<Role> role);
    boolean validateJwtToken(SecretKey secretKey, AcessToken acessToken);
    String getUsernameFromJwtToken(SecretKey secretKey,AcessToken acessToken);
}
