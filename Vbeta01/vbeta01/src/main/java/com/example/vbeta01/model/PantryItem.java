package com.example.vbeta01.model;

import java.time.LocalDate;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "PANTRY_ITEMS")
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
public class PantryItem {
    @EqualsAndHashCode.Exclude

    @Id @GeneratedValue
    Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Ingredient ingredient;

    Double amount;
    String unit;
    LocalDate expirationDate;
    String notes;
    

    @PostConstruct
    public void debug() {
        System.out.println("Pantry entity LOADED");
    }
}
