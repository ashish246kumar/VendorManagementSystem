package com.kamjritztex.vendor_management_system.util.security;

import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface TokenProvider {
    AcessToken createToken(String userName, Set<Role> role);
    AcessToken getTokenFromHeader(HttpServletRequest httpServletRequest);
   boolean validateToken(AcessToken acessToken);
   Authentication getAuthentication(AcessToken acessToken);

}
