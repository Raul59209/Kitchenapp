package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private ArrayList<Recipe> perfect;
    ArrayList<Recipe> almost;
    
    public Kitchen() {
        this.perfect = new ArrayList<>();
        this.almost = new ArrayList<>();
    }

    public List<Recipe> comparePantryAndRecipe(Pantry pantry, Cookbook cookbook) {

        List<Recipe> perfect = new ArrayList<>();
        List<Recipe> almost = new ArrayList<>();

        for (Recipe r : cookbook.getRecipes()) {
            int tally = 0;

            for (Ingredient needed : r.getPantry().getIngredients()) {
                for (Ingredient have : pantry.getIngredients()) {
                    if (have.getName().equalsIgnoreCase(needed.getName())) {
                        if (have.getAmount() >= needed.getAmount()) {
                            tally += 5;
                        } else {
                            tally += 2;
                        }
                    }
                }
            }

            r.setTally(tally);

            if (tally >= 5) perfect.add(r);
            else if (tally > 0) almost.add(r);
        }

        List<Recipe> result = new ArrayList<>();
        result.addAll(perfect);
        result.addAll(almost);

        return result;
    }

}
