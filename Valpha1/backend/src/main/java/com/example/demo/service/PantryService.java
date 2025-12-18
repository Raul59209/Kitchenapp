package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Pantry;
import com.example.demo.persistence.PantryRepository;

@Service
public class PantryService {
    private final PantryRepository repository;
    private Pantry pantry;

    public Pantry getPantry() {
    return pantry;
}

    public PantryService(PantryRepository repository) throws IOException {
        this.repository = repository;
        this.pantry = repository.load();
    }

    public void addIngredient(Ingredient i) throws IOException {
        pantry.add(i);
        repository.save(pantry);
    }

    public List<Ingredient> getAll() {
        return pantry.getIngredients();
    }

    public void removeByName(String name) throws IOException {
        pantry.removeByName(name);
        repository.save(pantry);
    }
}
