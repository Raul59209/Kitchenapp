package com.example.vbeta01.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vbeta01.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByName(String name);
}
