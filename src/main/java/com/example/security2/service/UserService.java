package com.example.security2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.security2.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.security2.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail("henamorado@gmail.com")
            .orElseThrow(() -> new EntityNotFoundException("User not found by email!!"));
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("User not found by username!!"));
    }
}
