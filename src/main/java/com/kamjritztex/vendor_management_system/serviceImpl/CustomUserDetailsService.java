package com.kamjritztex.vendor_management_system.serviceImpl;

import com.kamjritztex.vendor_management_system.model.Role;
import com.kamjritztex.vendor_management_system.model.User;
import com.kamjritztex.vendor_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("UserName Not Found"+username));
        Set<Role> roles=user.getRoles();
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getSimpleGrantedAuthorities(roles))
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }
    private Set<GrantedAuthority>getSimpleGrantedAuthorities(Set<Role> roles){
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        for(Role role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));

        }
        return grantedAuthorities;
    }
}
