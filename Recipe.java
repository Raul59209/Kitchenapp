package newer;

import java.util.Scanner;

public class Recipe {
    String title;
    Pantry ingred = new Pantry();
    String instructions;
    int tally;

    public String getTitle() {
        return this.title;
    }

    public Pantry getPantry() {
        return this.ingred;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public int getTally() {
        return this.tally;
    }

    public void setTitle(String x) {
        this.title = x;
    }

    public void setInstructions(String x) {
        this.instructions = x;
    }

    public Recipe(String tit, Pantry reci, String instro) {
        this.title = tit;
        this.ingred = reci;
        this.instructions = instro;
        this.tally = 0;
    }

    public static Recipe create() {
        Pantry newP = new Pantry();
        Scanner scanner = new Scanner(System.in);
        Scanner sca = new Scanner(System.in);

        System.out.println("Enter your recipe title.");
        String n = scanner.nextLine();
        System.out.println("How many ingredients do you need for this recipe?");
        int a = scanner.nextInt();

        for (int i = 0; i < a; i++) {
            newP.add(Ingredient.create());
        }

        System.out.println("Enter your recipe instructions.");
        String u = sca.nextLine();

        return new Recipe(n, newP, u);
    }
}
