package com.example.Financebackend.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Financebackend.DTO.CreateRecordRequest;
import com.example.Financebackend.DTO.UpdateRecordRequest;
import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Model.RecordType;
import com.example.Financebackend.Model.Role;
import com.example.Financebackend.Model.Status;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Repository.FinancialRecordRepository;
import com.example.Financebackend.Repository.UserRepository;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        return userRepository.findById(1L).get();
    }

    private void validateActive(User user) {
        if (user.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("User inactive");
        }
    }

    private void validateAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin allowed");
        }
    }

    // CREATE
    public FinancialRecord create(CreateRecordRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        FinancialRecord record = new FinancialRecord();
        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());
        record.setCreatedBy(currentUser);
        record.setCreatedAt(LocalDateTime.now());

        return recordRepository.save(record);
    }

    // GET ALL
    public List<FinancialRecord> getAll() {

        User currentUser = getCurrentUser();
        validateActive(currentUser);

        return recordRepository.findAll();
    }

    // UPDATE
    public FinancialRecord update(Long id, UpdateRecordRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        if (request.getAmount() != null)
            record.setAmount(request.getAmount());

        if (request.getType() != null)
            record.setType(request.getType());

        if (request.getCategory() != null)
            record.setCategory(request.getCategory());

        if (request.getDate() != null)
            record.setDate(request.getDate());

        if (request.getNotes() != null)
            record.setNotes(request.getNotes());

        return recordRepository.save(record);
    }

    // DELETE
    public void delete(Long id) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        recordRepository.deleteById(id);
    }

    // FILTER
    public List<FinancialRecord> filter(
            RecordType type,
            String category,
            LocalDate startDate,
            LocalDate endDate) {

        if (type != null) {
            return recordRepository.findByType(type);
        }

        if (category != null) {
            return recordRepository.findByCategory(category);
        }

        if (startDate != null && endDate != null) {
            return recordRepository.findByDateBetween(startDate, endDate);
        }

        return recordRepository.findAll();
    }
}