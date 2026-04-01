package com.example.Financebackend.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Financebackend.DTO.CreateUserRequest;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());
        user.setCreatedAt(LocalDateTime.now());
        
        userRepository.save(user);
        return user;
    }

}
