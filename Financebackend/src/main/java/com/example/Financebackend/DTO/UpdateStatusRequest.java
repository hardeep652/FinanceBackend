package com.example.Financebackend.DTO;

import com.example.Financebackend.Model.Status;

import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}