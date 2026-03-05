package com.example.vbeta01.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.vbeta01.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAll();
    List<Recipe> findByName(String name);
    List<Recipe> findByDescription(String description);
}
