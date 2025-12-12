package newer.Kitchenapp.Valpha0-8;

import java.util.ArrayList;

public class Pantry {
    ArrayList<Ingredient> pantry; 

    public Pantry(){
        this.pantry = new ArrayList<>();
    }

    public void add(Ingredient yummy) {
        pantry.add(yummy);
    }

    public void remove(Ingredient yummy) {
        pantry.remove(yummy);
    }

    public void show() {
        System.out.println("You have in your pantry: ");
        for (Ingredient i : pantry) {
            System.out.println(i.getName() + ": " + i.getAmount() + " " + i.getUnit());
        }
    }

}
