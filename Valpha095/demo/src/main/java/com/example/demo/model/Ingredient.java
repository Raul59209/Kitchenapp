package newer.Kitchenapp.Valpha095.demo.src.main.java.com.example.demo.model;

public class Ingredient {
    String name;
    double amount;
    String unit;

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Ingredient(String n, double a, String u)  {
        this.name = n;
        this.amount = a;
        this.unit = u;
    }
/*
    public static Ingredient create() {
        Scanner scanner = new Scanner(System.in);
        Scanner sca = new Scanner(System.in);
        System.out.println("Enter your yummies one at a time to be stocked in the Yummy-dungeon.");
        String n = scanner.nextLine();
        System.out.println("Enter your yummies amount one at a time to be stocked in the Yummy-dungeon.");
        double a = scanner.nextDouble();
        System.out.println("Enter your yummies unit one at a time to be stocked in the Yummy-dungeon.");
        String u = sca.nextLine();
        //scanner.close();
        //sca.close();
        return new Ingredient(n, a, u);
    }
        */
}
