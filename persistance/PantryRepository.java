package com.example.vbeta01.persistance;
import org.springframework.data.repository.CrudRepository;

import com.example.vbeta01.model.Ingredient;

public interface PantryRepository extends CrudRepository<Ingredient, Long> {
    
}
