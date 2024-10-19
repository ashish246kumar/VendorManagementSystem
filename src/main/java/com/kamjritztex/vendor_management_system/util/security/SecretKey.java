package com.kamjritztex.vendor_management_system.util.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretKey {
    private String secretKey;
    private long expirationInMiliseconds;
}
