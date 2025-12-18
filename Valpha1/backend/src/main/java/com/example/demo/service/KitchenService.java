package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.*;

@Service
public class KitchenService {
    private final PantryService pantryService;
    private final CookbookService cookbookService;

    public KitchenService(PantryService pantryService, CookbookService cookbookService) {
        this.pantryService = pantryService;
        this.cookbookService = cookbookService;
    }

    public List<Recipe> compare() {
        Pantry pantry = pantryService.getPantry();
        Cookbook cookbook = cookbookService.getCookbook();

        Kitchen kitchen = new Kitchen();
        return kitchen.comparePantryAndRecipe(pantry, cookbook);
    }
}
