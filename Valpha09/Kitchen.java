package newer.Kitchenapp.Valpha09;

import java.util.ArrayList;

public class Kitchen {
    ArrayList<Recipe> perfect;
    ArrayList<Recipe> almost;
    
    public Kitchen() {
        this.perfect = new ArrayList<>();
        this.almost = new ArrayList<>();
    }

    public void comparePantryAndRecipe(Pantry pantry, Cookbook recipe) {
        for (Ingredient i : pantry.pantry) {                                                        // for every ingredient in pantry
            for (Recipe r : recipe.recipes) {                                                       // for every recipe in the cookbook
                for (Ingredient j : r.ingred.pantry) {                                              // for every ingredient in that recipe
                    if (i.getName().equals(j.getName()) && i.getAmount() >= j.getAmount()) {        // if names match and pantry has enough
                        r.tally += 5;
                        perfect.add(r);
                    } else if (i.getName().equals(j.getName()) && i.getAmount() < j.getAmount()) {  // if names match but pantry doesn't have enough
                        r.tally += 2;
                        almost.add(r);
                    }
                }
            }
        } System.out.println("Based on your pantry, you can make:" );
        System.out.println("Perfect Matches:");
        for (Recipe p : perfect) {
            System.out.println(p.getTitle() + " with a tally of: " + p.getTally());
        }
        System.out.println("Almost Matches:");
        for (Recipe a : almost) {
            System.out.println(a.getTitle() + " with a tally of: " + a.getTally());
        }
    }
}
