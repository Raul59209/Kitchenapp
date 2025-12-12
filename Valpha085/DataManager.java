package newer.Kitchenapp.Valpha085;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataManager {
    private static String pantryManager = "pantry.json";
    private static String cookbookManager = "cookbook.json";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void savePantry(Pantry pantry) {
        try (FileWriter writer = new FileWriter(pantryManager)) {
            gson.toJson(pantry, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pantry loadPantry() {
        try (FileReader reader = new FileReader(pantryManager)) {
            return gson.fromJson(reader, Pantry.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Pantry();
        }
    }

    public static void saveCookbook(Cookbook cookbook) {
        try (FileWriter writer = new FileWriter(cookbookManager)) {
            gson.toJson(cookbook, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cookbook loadCookbook() {
        try (FileReader reader = new FileReader(cookbookManager)) {
            return gson.fromJson(reader, Cookbook.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Cookbook("My Cookbook");
        }
    }
}
