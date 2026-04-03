package com.example.Financebackend.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Financebackend.Config.JwtFilter;
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

    public Page<FinancialRecord> getAll(int page, int size) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);

        Pageable pageable = PageRequest.of(page, size);

        return recordRepository.findByCreatedBy(currentUser, pageable);
    }

    public FinancialRecord update(Long id, UpdateRecordRequest request) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND: Record not found"));

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

    public void delete(Long id) {

        User currentUser = getCurrentUser();
        validateActive(currentUser);
        validateAdmin(currentUser);

        if (!recordRepository.existsById(id)) {
            throw new RuntimeException("NOT_FOUND: Record not found");
        }

        recordRepository.deleteById(id);
    }

   public Page<FinancialRecord> filter(
        RecordType type,
        String category,
        LocalDate startDate,
        LocalDate endDate,
        int page,
        int size) {

    User currentUser = getCurrentUser();
    validateActive(currentUser);

    Pageable pageable = PageRequest.of(page, size);

    if (type != null) {
        return recordRepository.findByCreatedByAndType(currentUser, type, pageable);
    }

    if (category != null) {
        return recordRepository.findByCreatedByAndCategory(currentUser, category, pageable);
    }

    if (startDate != null && endDate != null) {
        return recordRepository.findByCreatedByAndDateBetween(currentUser, startDate, endDate, pageable);
    }

    return recordRepository.findByCreatedBy(currentUser, pageable);
}
}