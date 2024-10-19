package com.kamjritztex.vendor_management_system.config;


//import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.util.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           AcessToken accessToken=tokenProvider.getTokenFromHeader(request);
           try{
             if(checkAccessToken(accessToken)){
                 Authentication authentication=tokenProvider.getAuthentication(accessToken);
                 SecurityContextHolder.getContext().setAuthentication(authentication);

             }
             filterChain.doFilter(request,response);

           }
           catch (Exception e){
               SecurityContextHolder.clearContext();
            throw new CustomException("Exception come during filteration");

           }
    }
    private boolean checkAccessToken(AcessToken acessToken){
        if (acessToken==null){
            return false;
        }
        return tokenProvider.validateToken(acessToken);
    }
}
