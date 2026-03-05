package com.example.vbeta01.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vbeta01.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    List<Recipe> findByDescription(String description);
    // eventually an author
}
