package com.example.Financebackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Financebackend.DTO.CategoryBreakdownResponse;
import com.example.Financebackend.DTO.DashboardSummaryResponse;
import com.example.Financebackend.Model.FinancialRecord;
import com.example.Financebackend.Service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // SUMMARY
    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }

    // CATEGORY BREAKDOWN
    @GetMapping("/categories")
    public List<CategoryBreakdownResponse> getCategories() {
        return dashboardService.getCategoryBreakdown();
    }

    // RECENT ACTIVITY
    @GetMapping("/recent")
    public List<FinancialRecord> getRecent() {
        return dashboardService.getRecent();
    }
}