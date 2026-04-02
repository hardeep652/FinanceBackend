package com.example.Financebackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Financebackend.DTO.CreateUserRequest;
import com.example.Financebackend.DTO.UpdateRoleRequest;
import com.example.Financebackend.DTO.UpdateStatusRequest;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody CreateUserRequest request)
    {
        User createdUser = userService.create(request);
        return ResponseEntity.ok(createdUser);
    }


    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // UPDATE ROLE
    @PatchMapping("/{id}/role")
    public ResponseEntity<User> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleRequest request) {

        return ResponseEntity.ok(userService.updateUserRole(id, request));
    }

    // UPDATE STATUS
    @PatchMapping("/{id}/status")
    public ResponseEntity<User> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request) {

        return ResponseEntity.ok(userService.updateUserStatus(id, request));
    }
}
    

