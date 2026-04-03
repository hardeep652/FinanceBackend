package com.example.Financebackend.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Financebackend.Config.JwtFilter;
import com.example.Financebackend.DTO.CreateUserRequest;
import com.example.Financebackend.DTO.UpdateRoleRequest;
import com.example.Financebackend.DTO.UpdateStatusRequest;
import com.example.Financebackend.Model.Role;
import com.example.Financebackend.Model.Status;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        String email = JwtFilter.currentUserEmail.get();

        if (email == null) {
            throw new RuntimeException("UNAUTHORIZED: Please provide valid token");
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND: User not found"));
    }

    private void validateActive(User user) {
        if (user.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("FORBIDDEN: User is inactive");
        }
    }

    private void validateAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("FORBIDDEN: Only admin can perform this action");
        }
    }

    public User create(CreateUserRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("BAD_REQUEST: Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        return userRepository.findAll();
    }

    public User updateUserRole(Long id, UpdateRoleRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND: User not found"));

        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public User updateUserStatus(Long id, UpdateStatusRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND: User not found"));

        user.setStatus(request.getStatus());
        return userRepository.save(user);
    }
}