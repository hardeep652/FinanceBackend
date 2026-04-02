package com.example.Financebackend.DTO;

import java.math.BigDecimal;

public class CategoryBreakdownResponse {

    private String category;
    private BigDecimal total;

    public CategoryBreakdownResponse(String category, BigDecimal total) {
        this.category = category;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getTotal() {
        return total;
    }
}