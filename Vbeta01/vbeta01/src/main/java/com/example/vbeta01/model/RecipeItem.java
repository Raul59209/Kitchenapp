package com.example.vbeta01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "recipe_items")
@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RecipeItem {
    
    @Id @GeneratedValue
    Long id;

    @ManyToOne
    Recipe recipe;

    @ManyToOne
    Ingredient ingredient;

    Double amount;
    String unit;
}
