package com.example.demo.model;

import java.util.ArrayList;

public class Cookbook {
        private ArrayList<Recipe> recipes;
        private String name;

        public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cookbook() {
        this.recipes = new ArrayList<>();
        this.name = "My Cookbook";
    }

    public Cookbook(String x) {
        this.name = x;
        this.recipes = new ArrayList<>();
    }

        public void add(Recipe yummy) {
        recipes.add(yummy);
    }

    public void remove(Recipe yummy) {
        recipes.remove(yummy);
    }

    public void removeByName(String name) {
        recipes.removeIf(recipe -> recipe.getTitle().equalsIgnoreCase(name));
    }
/*
    public void show() {
        System.out.println("You have in your cookbook: ");
        for (Recipe i : recipes) {
            System.out.println(i.getTitle());
        }
    }

    public void removal() {
        System.out.print("Type the name of the recipe you would like to delete: ");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        s.close();
        
        for (Recipe i : recipes) {
            if (i.getTitle().equals(s.toString())) {
                recipes.remove(i);
                System.out.println(s.toString() + " has been removed from your cookbook.");
                return;
            }
        }       System.out.println(s.toString() + " not found in your cookbook.");
    }

    public void readRecipe() {
        Scanner s = new Scanner(System.in);
        System.out.print("Type the name of the recipe you would like to read: ");
        String recipeName = s.nextLine();
        s.close();
        for (Recipe i : recipes) {
            if (i.getTitle().equals(recipeName)) {
                System.out.println("Instructions for " + recipeName + ":");
                System.out.println(i.getInstructions());
                System.out.println("Ingredients needed:");
                i.getPantry().show();
                return;
            }
        }
        System.out.println(recipeName + " not found in your cookbook.");
    }
    */

}
