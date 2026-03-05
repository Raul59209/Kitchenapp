package com.example.vbeta01.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vbeta01.dto.RecipeItemDTO;
import com.example.vbeta01.mapper.RecipeItemMapper;
import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.model.Recipe;
import com.example.vbeta01.model.RecipeItem;
import com.example.vbeta01.persistance.IngredientRepository;
import com.example.vbeta01.persistance.RecipeItemRepository;
import com.example.vbeta01.persistance.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;




@RestController
@RequestMapping("recipes/{recipeId}/items")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RecipeItemController {
    RecipeRepository recipeRepository;
    RecipeItemRepository recipeItemRepository;
    IngredientRepository ingredientRepository;

    @GetMapping
    public List<RecipeItem> getItems(@PathVariable Long recipeId) {
        return recipeItemRepository.findByRecipeId(recipeId);
    }
    
    @GetMapping("/search")
    public List<RecipeItem> getItemsByIngredient(@PathVariable Long recipeId, @RequestParam Long ingredientId) {
        return recipeItemRepository.findByRecipeIdAndIngredientId(recipeId, ingredientId);
    }
    
    @PostMapping
    public RecipeItem addRecipeItem(@PathVariable Long recipeId, @RequestBody RecipeItemDTO dto) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        Ingredient ingredient = ingredientRepository.findById(dto.ingredientId()).orElseThrow();
        RecipeItem item = RecipeItemMapper.toEntity(dto, recipe, ingredient);
        return recipeItemRepository.save(item);
    }
    
    @PutMapping("/{recipeItemId}")
    public RecipeItem editRecipeItem(@PathVariable Long recipeItemId, @RequestBody RecipeItemDTO dto) {
        RecipeItem item = recipeItemRepository.findById(recipeItemId).orElseThrow();
        if (dto.amount() != null) { item.setAmount(dto.amount()); }
        if (dto.unit() != null) { item.setUnit(dto.unit()); }
        return recipeItemRepository.save(item);
    }

    @DeleteMapping("/{recipeItemId}")
    public void deleteRecipeItem(@PathVariable Long recipeItemId) {
        recipeItemRepository.deleteById(recipeItemId);
    }
}
