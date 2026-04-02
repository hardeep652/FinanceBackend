package com.example.Financebackend.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Financebackend.DTO.CreateRecordRequest;
import com.example.Financebackend.DTO.UpdateRecordRequest;
import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Model.RecordType;
import com.example.Financebackend.Service.FinancialRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService service;

    @PostMapping
    public FinancialRecord create(@Valid @RequestBody CreateRecordRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<FinancialRecord> getAll(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        return service.filter(type, category, startDate, endDate);
    }

    @PutMapping("/{id}")
    public FinancialRecord update(
            @PathVariable Long id,
            @RequestBody UpdateRecordRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}