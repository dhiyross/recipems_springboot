package dev.rossy.recipems.recipe;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // 1. Get All Recipes
    @GetMapping("")
    public ResponseEntity<List<RecipeResponseDTO>> getAllRecipes() {
        List<RecipeResponseDTO> recipes = recipeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    // 2. Get Recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> getRecipeById(@PathVariable Integer id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(value -> ResponseEntity.ok(convertToDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Create New Recipe
    @PostMapping("")
    public ResponseEntity<String> createRecipe(@Valid @RequestBody Recipe recipe) {
        recipe.setId(null);
        recipeRepository.save(recipe);
        return ResponseEntity.status(201).body("Recipe '" + recipe.getName() + "' created successfully!");
    }

    // 4. Update Existing Recipe
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable Integer id, @RequestBody Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setCuisine(updatedRecipe.getCuisine());
            recipe.setServings(updatedRecipe.getServings());
            recipe.setPrice(updatedRecipe.getPrice());
            recipe.setRating(updatedRecipe.getRating());
            recipe.setPrep_time_minutes(updatedRecipe.getPrep_time_minutes());
            recipe.setCook_time_minutes(updatedRecipe.getCook_time_minutes());
            recipe.setDifficulty(updatedRecipe.getDifficulty());
            recipe.setCalories_per_serving(updatedRecipe .getCalories_per_serving());
            recipe.setMeal_type(updatedRecipe.getMeal_type());
//            recipe.setReview_count(updatedRecipe.getReview_count());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            recipe.setTags(updatedRecipe.getTags());
            recipeRepository.save(recipe);
            return ResponseEntity.ok("Recipe '" + recipe.getName() + "' updated successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. Delete Recipe by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer id) {
        return recipeRepository.findById(id).map(recipe -> {
            recipeRepository.delete(recipe);
            return ResponseEntity.ok("Recipe '" + recipe.getName() + "' deleted successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // 7. Get Recipes by Cuisine
    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipesByCuisine(@PathVariable String cuisine) {
        List<RecipeResponseDTO> recipes = recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getCuisine().equalsIgnoreCase(cuisine))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }


    // 9. Get Recipes by Difficulty
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipesByDifficulty(@PathVariable String difficulty) {
        List<RecipeResponseDTO> recipes = recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getDifficulty().equalsIgnoreCase(difficulty))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    // Helper method to convert Recipe Entity to RecipeResponseDTO
    private RecipeResponseDTO convertToDTO(Recipe recipe) {
        RecipeResponseDTO dto = new RecipeResponseDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setCuisine(recipe.getCuisine());
        dto.setServings(recipe.getServings());
        dto.setPrice(recipe.getPrice());
        dto.setRating(recipe.getRating());
//        dto.setprep_time_minutes(recipe.getprep_time_minutes());
        dto.setPrep_time_minutes(recipe.getPrep_time_minutes());
        dto.setCook_time_minutes(recipe.getCook_time_minutes());
        dto.setDifficulty(recipe.getDifficulty());
//        dto.setCaloriesPerServing(recipe.getCaloriesPerServing());
        dto.setUser_id(recipe.getUser_id());
        dto.setImage(recipe.getImage());
        dto.setReview_count(recipe.getReview_count());
        dto.setMeal_type(recipe.getMeal_type());
        dto.setIngredients(recipe.getIngredients());
        dto.setInstructions(recipe.getInstructions());
        dto.setTags(recipe.getTags());
        return dto;
    }
}
