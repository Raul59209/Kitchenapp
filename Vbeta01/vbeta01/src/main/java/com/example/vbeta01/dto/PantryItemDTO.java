package com.example.vbeta01.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PantryItemDTO(Long ingredientId, Double amount, String unit, LocalDate expirationDate, String notes) {
    
}
