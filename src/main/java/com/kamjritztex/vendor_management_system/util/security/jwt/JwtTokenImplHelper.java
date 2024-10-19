package com.kamjritztex.vendor_management_system.util.security.jwt;

//import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.util.security.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenImplHelper implements JwtTokenHelper{

    @Override
    public String generateJwtToken(SecretKey secretKey, String userName, Set<Role> roles) {
        Claims claims= Jwts.claims().setSubject(userName);
        claims.put("authorities",roles.stream().map(role->{
            return new SimpleGrantedAuthority(role.name());
        }).collect(Collectors.toList()));
        Date issuedAt=new Date();
        Date validUntil=new Date(issuedAt.getTime()+secretKey.getExpirationInMiliseconds());
        String seccretKeyEncoded= Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
        return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt).setExpiration(validUntil).signWith(SignatureAlgorithm.HS256,seccretKeyEncoded).compact();
    }

    @Override
    public boolean validateJwtToken(SecretKey secretKey, AcessToken acessToken) {
        try {
            String secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
            Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(acessToken.getToken());
            return true;
        }
        catch (JwtException|IllegalArgumentException e){
//            throw new CustomException("Invalid Token", HttpStatus.INTERNAL_SERVER_ERROR);
              throw  e;
        }
    }

    @Override
    public String getUsernameFromJwtToken(SecretKey secretKey, AcessToken acessToken) {
        String secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
       return  Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(acessToken.getToken()).getBody().getSubject();
    }


}
