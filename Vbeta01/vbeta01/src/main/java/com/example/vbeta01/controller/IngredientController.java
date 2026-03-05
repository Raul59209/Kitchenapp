package com.example.vbeta01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.persistance.IngredientRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    //Get all ingredients
    @GetMapping
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Get ingredient by id
    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public Optional<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        return this.ingredientRepository.findById(id);
    }

    //Get ingredients by name
    @GetMapping("/search")
    public List<Ingredient> searchIngredients(@RequestParam("name") String name) {
        return this.ingredientRepository.findByName(name);
    }

    // Post new ingredient
    @PostMapping
    public Ingredient createNewIngredient(@RequestBody Ingredient ingredient) {
        if (ingredient.getId() != null) { return null; }
        Ingredient newIngredient = this.ingredientRepository.save(ingredient);
        return newIngredient;
    }

    // Put ingredient by id
    @PutMapping("{id}")
    public Ingredient updateIngredient(@PathVariable("id") Long id, @RequestBody Ingredient ing) {
        @SuppressWarnings("null")
        Optional<Ingredient> ingredientOptional = this.ingredientRepository.findById(id);
        if (!ingredientOptional.isPresent()) {
            return null;
        }
        Ingredient ingredientToUpdate = ingredientOptional.get();
        if (ing.getName() != null) {
            ingredientToUpdate.setName(ing.getName());
        }
        @SuppressWarnings("null")
        Ingredient updatedIngredient = this.ingredientRepository.save(ingredientToUpdate);
            return updatedIngredient;
    }

    // Delete ingredient by id
    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    public Ingredient deleteIngredient(@PathVariable("id") Long id) {
        @SuppressWarnings("null")
        Optional<Ingredient> ingredientOptional = this.ingredientRepository.findById(id);
        if (!ingredientOptional.isPresent()) {
            return null;
        }
        Ingredient ingredientToDelete = ingredientOptional.get();
        this.ingredientRepository.delete(ingredientToDelete);
        return ingredientToDelete;
    }
}

