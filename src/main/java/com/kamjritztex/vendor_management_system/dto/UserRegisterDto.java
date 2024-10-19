package com.kamjritztex.vendor_management_system.dto;

import com.kamjritztex.vendor_management_system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {


    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
}
