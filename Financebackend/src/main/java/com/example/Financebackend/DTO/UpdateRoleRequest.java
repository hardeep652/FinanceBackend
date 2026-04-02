package com.example.Financebackend.DTO;

import com.example.Financebackend.Model.Role;

import jakarta.validation.constraints.NotNull;

public class UpdateRoleRequest {

    @NotNull
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}