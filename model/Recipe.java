package com.example.vbeta01.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    
    String name;
    String instructions;
    String description;

    @ManyToMany
    List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {}

    public Recipe(Long id, String name, String instro, String descrip, List<Ingredient> ingred) {
        this.id = id;
        this.name = name;
        this.instructions = instro;
        this.description = descrip;
        this.ingredients = ingred;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
            return this.name;
        }
    public void setName(String x) {
        this.name = x;
    }

    public String getInstructions() {
        return this.instructions;
    }
    public void setInstructions(String x) {
        this.instructions = x;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String x) {
        this.description = x;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
