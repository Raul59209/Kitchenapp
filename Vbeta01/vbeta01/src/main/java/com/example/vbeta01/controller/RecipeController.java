package com.example.vbeta01.controller;

import java.util.ArrayList;
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

import com.example.vbeta01.dto.RecipeDTO;
import com.example.vbeta01.dto.RecipeItemDTO;
import com.example.vbeta01.mapper.RecipeItemMapper;
import com.example.vbeta01.mapper.RecipeMapper;
import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.model.Recipe;
import com.example.vbeta01.model.RecipeItem;
import com.example.vbeta01.persistance.IngredientRepository;
import com.example.vbeta01.persistance.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/recipes")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class RecipeController {
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;

    //Get all recipes
    @GetMapping
    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Get recipe by id
    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return this.recipeRepository.findById(id).orElseThrow();
    }

    //Get recipes by name or description
    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam(name="name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return this.recipeRepository.findByName(name);
        }
        return this.recipeRepository.findAll();
    }

    // Post new recipe
    @PostMapping
    // dto has items that we need to add to our recipe constructor. 
    // Before we do that, we need to convert those items to entities and save them to a List. 
    // Then we can add them to our recipe constructor and save the recipe to the database.
    public Recipe createNewRecipe(@RequestBody RecipeDTO dto) { 
        List<RecipeItem> items = new ArrayList<>(); // ArrayList to hold the recipe items that we will create from the DTO
        for (RecipeItemDTO itemDto: dto.items()) { // Loop through items in the dto
            @SuppressWarnings("null")
            Ingredient ingredient = ingredientRepository.findById(itemDto.ingredientId()).orElseThrow(); // create an ingredient entity from the ingredient id in the item DTO
            RecipeItem item = RecipeItemMapper.toEntity(itemDto, null, ingredient);
            items.add(item); // add the item to the list of items
        }
        Recipe recipe = RecipeMapper.toEntity(dto, items);
        items.forEach(i -> i.setRecipe(recipe)); // set the recipe for each item to be the recipe we just created
        @SuppressWarnings("null")
        Recipe newRecipe = this.recipeRepository.save(recipe); // save the recipe to the database, which will also save the items because of the cascade type we set in the Recipe entity
        return newRecipe;
    }

    // This is the advanced version that I don't understand yet.
    // @PostMapping
    // public Recipe createRecipe(@RequestBody RecipeDTO dto) {

    //     // 1. Convert each RecipeItemDTO into a RecipeItem entity
    //     List<RecipeItem> items = dto.items().stream()
    //         .map(itemDto -> {
    //             Ingredient ingredient = ingredientRepository
    //                 .findById(itemDto.ingredientId())
    //                 .orElseThrow();

    //             // recipe is null for now — we attach it later
    //             return RecipeItemMapper.toEntity(itemDto, null, ingredient);
    //         })
    //         .toList();

    //     // 2. Create the Recipe entity
    //     Recipe recipe = RecipeMapper.toEntity(dto, items);

    //     // 3. Attach each item back to the recipe
    //     items.forEach(i -> i.setRecipe(recipe));

    //     // 4. Save everything
    //     return recipeRepository.save(recipe);
    // }

    // Put recipe by id
    @PutMapping("{id}")
    public Recipe updateRecipe(@PathVariable("id") @NonNull Long id, @RequestBody RecipeDTO dto) {
        Recipe item = recipeRepository.findById(id).orElseThrow();
        if (dto.name() != null) { item.setName(dto.name()); }
        if (dto.description() != null) { item.setDescription(dto.description()); }
        if (dto.instructions() != null) { item.setInstructions(dto.instructions()); }
        @SuppressWarnings("null")
        Recipe saved = this.recipeRepository.save(item);
        return saved;
    }

    // Delete recipe by id
    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    public void deleteRecipe(@PathVariable("id") Long id) {
        this.recipeRepository.deleteById(id);
    }
}
