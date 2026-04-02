package com.example.Financebackend.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Financebackend.DTO.CategoryBreakdownResponse;
import com.example.Financebackend.DTO.DashboardSummaryResponse;
import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Model.RecordType;
import com.example.Financebackend.Model.Status;
import com.example.Financebackend.Model.User;
import com.example.Financebackend.Repository.FinancialRecordRepository;
import com.example.Financebackend.Repository.UserRepository;

@Service
public class DashboardService {

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

    // 🔥 1. SUMMARY API
    public DashboardSummaryResponse getSummary() {

        User currentUser = getCurrentUser();
        validateActive(currentUser);

        List<FinancialRecord> records = recordRepository.findAll();

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (FinancialRecord record : records) {
            if (record.getType() == RecordType.INCOME) {
                totalIncome = totalIncome.add(record.getAmount());
            } else {
                totalExpense = totalExpense.add(record.getAmount());
            }
        }

        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        return new DashboardSummaryResponse(totalIncome, totalExpense, netBalance);
    }

    // 🔥 2. CATEGORY BREAKDOWN
    public List<CategoryBreakdownResponse> getCategoryBreakdown() {

        User currentUser = getCurrentUser();
        validateActive(currentUser);

        List<FinancialRecord> records = recordRepository.findAll();

        Map<String, BigDecimal> categoryMap = new HashMap<>();

        for (FinancialRecord record : records) {
            categoryMap.put(
                record.getCategory(),
                categoryMap.getOrDefault(record.getCategory(), BigDecimal.ZERO)
                        .add(record.getAmount())
            );
        }

        return categoryMap.entrySet().stream()
                .map(entry -> new CategoryBreakdownResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    // 🔥 3. RECENT ACTIVITY
    public List<FinancialRecord> getRecent() {

        User currentUser = getCurrentUser();
        validateActive(currentUser);

        List<FinancialRecord> records = recordRepository.findAll();

        return records.stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(5)
                .toList();
    }
}