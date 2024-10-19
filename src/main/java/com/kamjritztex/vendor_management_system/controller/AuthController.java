package com.kamjritztex.vendor_management_system.controller;

import com.kamjritztex.vendor_management_system.dto.UserLoginDto;
import com.kamjritztex.vendor_management_system.dto.UserRegisterDto;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto){
       log.info("userRegisterDTO{}",userRegisterDto);
      AcessToken accessToken=authService.register(userRegisterDto);
       return ResponseEntity.ok(accessToken);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        AcessToken acessToken=authService.login(userLoginDto);
        return ResponseEntity.ok(acessToken);
    }

}
