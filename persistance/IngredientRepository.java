package com.example.vbeta01.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.vbeta01.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    
    List<Ingredient> findByName(String name);
}
