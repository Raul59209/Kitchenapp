package com.example.vbeta01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vbeta01.model.Recipe;
import com.example.vbeta01.persistance.RecipeRepository;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // == Recipe Endpoints ==

    //Return all recipes
    @GetMapping("/recipes")
    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // return recipe by id
    @GetMapping("/recipes/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable("id") Long id) {
        return this.recipeRepository.findById(id);
    }

    //return recipes by name or description
    @GetMapping("/recipes/search")
    public List<Recipe> searchRecipes(@RequestParam(name="name", required = false) String name, @RequestParam(name="description", required = false) String description) {
        if (name != null && !name.isEmpty()) {
            return this.recipeRepository.findByName(name);
        } else if (description != null && !description.isEmpty()) {
            return this.recipeRepository.findByDescription(description);
        }
        return this.recipeRepository.findAll();
    }

    // Create new recipe
    @PostMapping("/recipes")
    public Recipe createNewRecipe(@RequestBody Recipe recipe) {
        Optional<Recipe> recOptional = this.recipeRepository.findById(recipe.getId());
        if (!recOptional.isPresent()) { return null; }
        Recipe newRecipe = this.recipeRepository.save(recipe);
        return newRecipe;
    }

    // Update recipe by id
    @PutMapping("/recipes/{id}")
    public Recipe updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe rec) {
        Optional<Recipe> recOptional = this.recipeRepository.findById(id);
        if (!recOptional.isPresent()) {
            return null;
        }
        Recipe recToUpdate = recOptional.get();
        if (rec.getName() != null) {
            recToUpdate.setName(rec.getName());
        }
        if (rec.getInstructions() != null) {
            recToUpdate.setInstructions(rec.getInstructions());
        }
        if (rec.getDescription() != null) {
            recToUpdate.setDescription(rec.getDescription());
        }
        Recipe updatedRecipe = this.recipeRepository.save(recToUpdate);
            return updatedRecipe;
    }

    // Delete recipe by id
    @DeleteMapping("/recipes/{id}")
    public Recipe deleteRecipe(@PathVariable("id") Long id) {
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(id);
        if (!recipeOptional.isPresent()) {
            return null;
        }
        Recipe recipeToDelete = recipeOptional.get();
        this.recipeRepository.delete(recipeToDelete);
        return recipeToDelete;
    }
}
