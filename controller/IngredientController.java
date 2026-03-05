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

import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.persistance.IngredientRepository;

@RestController
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // == Ingredient Endpoints ==

    //Return all ingredients
    @GetMapping("/ingredients")
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // return ingredient by id
    @GetMapping("/ingredients/{id}")
    public Optional<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        return this.ingredientRepository.findById(id);
    }

    //return ingredients by name
    @GetMapping("/ingredients/search")
    public List<Ingredient> searchIngredients(@RequestParam("name") String name) {
        return this.ingredientRepository.findByName(name);
    }

    // Create new ingredient
    @PostMapping("/ingredients")
    public Ingredient createNewIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = this.ingredientRepository.save(ingredient);
        return newIngredient;
    }

    // Update ingredient by id
    @PutMapping("/ingredients/{id}")
    public Ingredient updateIngredient(@PathVariable("id") Long id, @RequestBody Ingredient ing) {
        Optional<Ingredient> ingredientOptional = this.ingredientRepository.findById(id);
        if (!ingredientOptional.isPresent()) {
            return null;
        }
        Ingredient ingredientToUpdate = ingredientOptional.get();
        if (ing.getName() != null) {
            ingredientToUpdate.setName(ing.getName());
        }
        if (ing.getAmount() != null) {
            ingredientToUpdate.setAmount(ing.getAmount());
        }
        if (ing.getUnit() != null) {
            ingredientToUpdate.setUnit(ing.getUnit());
        }
        Ingredient updatedIngredient = this.ingredientRepository.save(ingredientToUpdate);
            return updatedIngredient;
    }

    // Delete ingredient by id
    @DeleteMapping("/ingredients/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") Long id) {
        Optional<Ingredient> ingredientOptional = this.ingredientRepository.findById(id);
        if (!ingredientOptional.isPresent()) {
            return null;
        }
        Ingredient ingredientToDelete = ingredientOptional.get();
        this.ingredientRepository.delete(ingredientToDelete);
        return ingredientToDelete;
    }
}

