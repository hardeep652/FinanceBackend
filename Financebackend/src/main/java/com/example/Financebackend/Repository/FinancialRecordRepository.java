package com.example.Financebackend.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Model.RecordType;
import com.example.Financebackend.Model.User;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

List<FinancialRecord> findByCreatedBy(User user);

    List<FinancialRecord> findByType(RecordType type);
        // Filter by category
    List<FinancialRecord> findByCategory(String category);

    // Filter by date range
    List<FinancialRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Combined filters (very useful)
    List<FinancialRecord> findByCreatedByAndType(User user, RecordType type);

    List<FinancialRecord> findByCreatedByAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

}
