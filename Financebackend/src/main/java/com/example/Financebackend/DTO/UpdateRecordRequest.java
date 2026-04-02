package com.example.Financebackend.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.Financebackend.Model.RecordType;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UpdateRecordRequest {

    private BigDecimal amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String notes;

    // getters & setters
}