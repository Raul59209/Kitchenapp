package newer.Kitchenapp.Valpha0-8;

import java.util.ArrayList;

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

}
