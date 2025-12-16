package newer.Kitchenapp.Valpha09;

import java.util.ArrayList;
import java.util.Scanner;

public class Cookbook {
        ArrayList<Recipe> recipes;
        String name;

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
