package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cookbook;
import com.example.demo.model.Recipe;
import com.example.demo.persistence.CookbookRepository;

@Service
public class CookbookService {
    private final CookbookRepository repository;
    private Cookbook cookbook;

    public Cookbook getCookbook() {
        return cookbook;
    }

    public CookbookService(CookbookRepository repository) throws IOException {
        this.repository = repository;
        this.cookbook =  repository.load();
    }

    public List<Recipe> getAll() {
        return cookbook.getRecipes();
    }

    public void add(Recipe recipe) throws IOException {
        cookbook.add(recipe);
        repository.save(cookbook);
    }

    public void remove(String name) throws IOException {
        cookbook.removeByName(name);
        repository.save(cookbook);
    }

}
