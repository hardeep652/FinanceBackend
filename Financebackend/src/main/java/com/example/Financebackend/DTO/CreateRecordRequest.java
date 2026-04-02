package com.example.Financebackend.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.Financebackend.Model.RecordType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class CreateRecordRequest {

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private RecordType type;

    @NotNull
    private String category;

    @NotNull
    private LocalDate date;

    private String notes;

    // getters & setters
}