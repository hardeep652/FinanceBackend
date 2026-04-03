package com.example.Financebackend.Config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Financebackend.Model.Role;
import com.example.Financebackend.Model.Status;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {

            if (userRepository.findByEmail("admin@example.com").isEmpty()) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setRole(Role.ADMIN);
                admin.setStatus(Status.ACTIVE);
                admin.setCreatedAt(LocalDateTime.now());

                userRepository.save(admin);

                System.out.println("🔥 Admin created");
            }
        };
    }
}