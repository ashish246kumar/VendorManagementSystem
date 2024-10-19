package com.kamjritztex.vendor_management_system.util.security.jwt;

import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.util.security.SecretKey;
import com.kamjritztex.vendor_management_system.util.security.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKeyValue;
    @Value("${security.jwt.token.expiration}")
    private long expiration;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public AcessToken createToken(String userName, Set<Role> role) {
        SecretKey secretKey=new SecretKey(secretKeyValue,expiration);
      String token=  jwtTokenHelper.generateJwtToken(secretKey,userName,role);
      return new AcessToken(token);
    }

    @Override
    public AcessToken getTokenFromHeader(HttpServletRequest httpServletRequest) {
        String bearerToken=httpServletRequest.getHeader("Authorization");
        if(bearerToken==null){
            return null;
        }
        if(!bearerToken.startsWith("Bearer "))return null;
        return new AcessToken(bearerToken.substring(7));
    }

    @Override
    public boolean validateToken(AcessToken acessToken) {
        SecretKey secretKey=new SecretKey(secretKeyValue,expiration);
        return jwtTokenHelper.validateJwtToken(secretKey,acessToken);
    }

    @Override
    public Authentication getAuthentication(AcessToken acessToken) {
        SecretKey secretKey=new SecretKey(secretKeyValue,expiration);
        String userName=jwtTokenHelper.getUsernameFromJwtToken(secretKey,acessToken);
        UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
        Authentication authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        return authentication;
    }

}
