package com.example.Financebackend.Repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Model.RecordType;
import com.example.Financebackend.Model.User;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    // 🔥 PAGINATION BASE
    Page<FinancialRecord> findByCreatedBy(User user, Pageable pageable);

    // 🔥 FILTER + PAGINATION
    Page<FinancialRecord> findByCreatedByAndType(User user, RecordType type, Pageable pageable);

    Page<FinancialRecord> findByCreatedByAndCategory(User user, String category, Pageable pageable);

    Page<FinancialRecord> findByCreatedByAndDateBetween(User user, LocalDate startDate, LocalDate endDate, Pageable pageable);

}