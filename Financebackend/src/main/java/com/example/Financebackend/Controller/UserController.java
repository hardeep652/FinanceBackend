package com.example.Financebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Financebackend.DTO.CreateUserRequest;
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
    
}
