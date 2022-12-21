package com.example.security2.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.security2.domain.User user = userService.getUserByEmail(email);
        return new User(
            user.getUsername(), 
            user.getPassword(), 
            user.isEnabled(), 
            true, true, true,
             user.getAuthorities()
             );
    }
    
}
