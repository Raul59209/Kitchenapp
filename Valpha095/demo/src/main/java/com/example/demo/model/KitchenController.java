package newer.Kitchenapp.Valpha095.demo.src.main.java.com.example.demo.model;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allows your Nuxt app to talk to Java
public class KitchenController {

    // Ideally, these should be in a Service class, but for now, we keep it simple
    private Pantry pantry = new Pantry(); 
    private Cookbook cookbook = new Cookbook("My Yummy Dungeon");

    // 1. Get Pantry contents
    @GetMapping("/pantry")
    public List<Ingredient> getPantry() {
        return pantry.getPantryList(); // Ensure Pantry class has a getter for the ArrayList
    }

    // 2. Add to Pantry
    @PostMapping("/pantry")
    public Ingredient addToPantry(@RequestBody Ingredient ingredient) {
        pantry.add(ingredient);
        // Call your DataManager.save(pantry) here later!
        return ingredient;
    }

    // 3. Get Recipes
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return cookbook.getRecipesList(); // Ensure Cookbook has a getter
    }
}