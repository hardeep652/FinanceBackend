package com.example.Financebackend.DTO;

import java.math.BigDecimal;

public class DashboardSummaryResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal netBalance;

    public DashboardSummaryResponse(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal netBalance) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netBalance = netBalance;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public BigDecimal getNetBalance() {
        return netBalance;
    }
}