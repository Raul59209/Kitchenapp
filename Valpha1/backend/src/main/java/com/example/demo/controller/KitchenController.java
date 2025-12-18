package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.service.CookbookService;
import com.example.demo.service.KitchenService;
import com.example.demo.service.PantryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allow Vue for now
public class KitchenController {

    private final PantryService pantryService;
    private final CookbookService cookbookService;
    private final KitchenService kitchenService;

    public KitchenController(
            PantryService pantryService,
            CookbookService cookbookService,
            KitchenService kitchenService
    ) {
        this.pantryService = pantryService;
        this.cookbookService = cookbookService;
        this.kitchenService = kitchenService;
    }

    // === CASE 1: Show pantry ===
    @GetMapping("/pantry")
    public List<Ingredient> showPantry() {
        return pantryService.getAll();
    }

    // === CASE 2: Add ingredient ===
    @PostMapping("/pantry")
    public void addIngredient(@RequestBody Ingredient ingredient) throws IOException {
        pantryService.addIngredient(ingredient);
    }

    // === CASE 3: Remove ingredient ===
    @DeleteMapping("/pantry/{name}")
    public void removeIngredient(@PathVariable String name) throws IOException {
        pantryService.removeByName(name);
    }

    // === CASE 4: Show recipes ===
    @GetMapping("/recipes")
    public List<Recipe> showRecipes() {
        return cookbookService.getAll();
    }

    // === CASE 6: Add recipe ===
    @PostMapping("/recipes")
    public void addRecipe(@RequestBody Recipe recipe) throws IOException {
        cookbookService.add(recipe);
    }

    // === CASE 8: Compare pantry & recipes ===
    @GetMapping("/compare")
    public List<Recipe> compare() {
        return kitchenService.compare();
    }
}
