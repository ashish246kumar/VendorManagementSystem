package com.kamjritztex.vendor_management_system.service;

import com.kamjritztex.vendor_management_system.dto.UserLoginDto;
import com.kamjritztex.vendor_management_system.dto.UserRegisterDto;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
public interface AuthService {

    AcessToken register(UserRegisterDto userRegisterDto);
    AcessToken login(UserLoginDto userLoginDto);

}
