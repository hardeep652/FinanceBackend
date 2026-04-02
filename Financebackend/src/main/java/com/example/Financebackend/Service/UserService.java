package com.example.Financebackend.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Financebackend.DTO.CreateUserRequest;
import com.example.Financebackend.DTO.UpdateRoleRequest;
import com.example.Financebackend.DTO.UpdateStatusRequest;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserRole(Long id, UpdateRoleRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public User updateUserStatus(Long id, UpdateStatusRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(request.getStatus());
        return userRepository.save(user);
    }

}
