package com.kamjritztex.vendor_management_system.serviceImpl;

import com.kamjritztex.vendor_management_system.dto.UserLoginDto;
import com.kamjritztex.vendor_management_system.dto.UserRegisterDto;

import com.kamjritztex.vendor_management_system.exception.CustomException;
import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.model.User;
import com.kamjritztex.vendor_management_system.repository.UserRepository;
import com.kamjritztex.vendor_management_system.security.jwt.AcessToken;
import com.kamjritztex.vendor_management_system.service.AuthService;
import com.kamjritztex.vendor_management_system.util.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

   @Autowired
  private UserRepository userRepository;
   @Autowired
   private TokenProvider tokenProvider;
   @Autowired
   private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AcessToken register(UserRegisterDto userRegisterDto) {
        isUserExit(userRegisterDto.getUsername());
       User user=User.builder()
               .username(userRegisterDto.getUsername())
               .email(userRegisterDto.getEmail())
               .password(passwordEncoder.encode(userRegisterDto.getPassword()))
               .roles(new HashSet<>(userRegisterDto.getRoles()))
               .build();
        userRepository.save(user);
        String userName=user.getUsername();
        Set<Role> roles=new HashSet<>(userRegisterDto.getRoles());
       return tokenProvider.createToken(userName,roles);

    }

    @Override
    public AcessToken login(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            Set<Role> roles=userRepository.findByUsername(userLoginDto.getUsername()).get().getRoles();
            return tokenProvider.createToken(userLoginDto.getUsername(),roles);
        }
        catch (Exception exception){
                throw new CustomException("Bad Credentaail",HttpStatus.BAD_REQUEST);

        }

    }

    private void isUserExit(String userName){
        if(userRepository.existsByUsername(userName)){
           throw new CustomException("user already Exist", HttpStatus.BAD_REQUEST);
        }
    }
}
