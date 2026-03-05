package com.example.vbeta01.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pantries")
public class Pantry {
    
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ingredient ingredient;

    private Double amount;

    public Pantry() {}

    public Pantry(Long id, User user, Ingredient ingredient, Double amount) {
        this.id = id;
        this.user = user;
        this.ingredient = ingredient;
        this.amount = amount;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Ingredient getIngredient() {
        return ingredient;
    }
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @PostConstruct
public void debug() {
    System.out.println("Pantry entity LOADED");
}

}
