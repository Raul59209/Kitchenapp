package com.example.vbeta01.mapper;

import java.util.List;

import com.example.vbeta01.dto.RecipeDTO;
import com.example.vbeta01.model.Recipe;
import com.example.vbeta01.model.RecipeItem;

public class RecipeMapper {
    
    public static Recipe toEntity(RecipeDTO dto, List<RecipeItem> items) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.name());
        recipe.setDescription(dto.description());
        recipe.setInstructions(dto.instructions());
        recipe.setItems(items);
        return recipe;
        // eventually add an author
    }
}
