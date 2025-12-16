package newer.Kitchenapp.Valpha09;

import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("Ingredients: ");
        for (Ingredient i : pantry) {
            System.out.println(i.getName() + ": " + i.getAmount() + " " + i.getUnit());
        }
    }

    public void removal() {
        System.out.println("Do you want to remove an item entirely or reduce the amount? (Type 'entirely' or 'reduce')");
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        switch (choice) {
            case "entirely" ->                 {
                    System.out.println("Enter the name of the ingredient to remove:");
                    String name = s.nextLine();
                    for (Ingredient i : pantry) {
                        if (i.getName().equals(name)) {
                            pantry.remove(i);
                            System.out.println(name + " has been removed from your pantry.");
                            return;
                        }
                    }       System.out.println(name + " not found in your pantry.");
                }
            case "reduce" ->                 {
                    System.out.println("Enter the name of the ingredient to reduce:");
                    String name = s.nextLine();
                    System.out.println("Enter the amount to reduce:");
                    double amountToReduce = s.nextDouble();
                    for (Ingredient i : pantry) {
                        if (i.getName().equals(name)) {
                            if (i.getAmount() > amountToReduce) {
                                i.setAmount(i.getAmount() - amountToReduce);
                                System.out.println("Reduced " + name + " by " + amountToReduce + ". New amount: " + i.getAmount());
                            } else {
                                pantry.remove(i);
                                System.out.println(name + " has been removed from your pantry as the amount reached zero.");
                            }
                            return;
                        }
                    }       System.out.println(name + " not found in your pantry.");
                }
            default -> System.out.println("Invalid choice. Please type 'entirely' or 'reduce'.");
        } s.close();
    }

}
