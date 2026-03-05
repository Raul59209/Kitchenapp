package com.example.vbeta01.persistance;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vbeta01.model.RecipeItem;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long>{
    List<RecipeItem> findByRecipeId(Long recipeId);
    List<RecipeItem> findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
