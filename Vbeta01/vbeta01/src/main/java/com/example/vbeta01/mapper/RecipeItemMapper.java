package com.example.vbeta01.mapper;

import com.example.vbeta01.dto.RecipeItemDTO;
import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.model.Recipe;
import com.example.vbeta01.model.RecipeItem;

public class RecipeItemMapper {
    
    public static RecipeItem toEntity(RecipeItemDTO dto, Recipe recipe, Ingredient ingredient) {
        RecipeItem item = new RecipeItem();
        item.setRecipe(recipe);
        item.setIngredient(ingredient);
        item.setAmount(dto.amount());
        item.setUnit(dto.unit());
        return item;
    }
}
