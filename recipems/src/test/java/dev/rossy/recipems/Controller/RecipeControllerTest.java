package dev.rossy.recipems.Controller;

import dev.rossy.recipems.recipe.Recipe;
import dev.rossy.recipems.recipe.RecipeController;
import dev.rossy.recipems.recipe.RecipeRepository;
import dev.rossy.recipems.recipe.RecipeResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void testGetAllRecipes_ShouldReturn200() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Pancake");
        recipe.setIngredients(Arrays.asList("flour", "sugar", "milk"));
        recipe.setInstructions(Arrays.asList("Mix ingredients","Bake at 350F", "Serve"));
        recipe.setTags(Arrays.asList("Breakfast", "Cake", "Milk"));
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe));

        // Act
        ResponseEntity<?> response = recipeController.getAllRecipes();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Iterable<?>);
    }

    @Test
    void testGetRecipeById_ShouldReturn200() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Pancake");
        recipe.setIngredients(Arrays.asList("flour", "sugar", "milk"));
        recipe.setInstructions(Arrays.asList("Mix ingredients", "Bake at 350F", "Serve"));
        recipe.setTags(Arrays.asList("Breakfast", "Cake", "Milk"));
        when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe));

        // Act
        ResponseEntity<?> response = recipeController.getRecipeById(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof RecipeResponseDTO);

        RecipeResponseDTO dto = (RecipeResponseDTO) response.getBody();
        assertEquals(1, dto.getId());
        assertEquals("Pancake", dto.getName());
        assertEquals(Arrays.asList("flour", "sugar", "milk"), dto.getIngredients());
        assertEquals(Arrays.asList("Mix ingredients", "Bake at 350F", "Serve"), dto.getInstructions());
    }


    @Test
    void testGetRecipeById_ShouldReturn404() {
        // Arrange
        when(recipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = recipeController.getRecipeById(1);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateRecipe_ShouldReturn201() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setName("Pancake");
        recipe.setPrep_time_minutes(10);
        recipe.setCook_time_minutes(20);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        // Act
        ResponseEntity<?> response = recipeController.createRecipe(recipe);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Recipe 'Pancake' created successfully!", response.getBody());
    }

    @Test
    void testUpdateRecipe_ShouldReturn200() {
        // Arrange
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(1);
        existingRecipe.setName("Old Pancake");
        existingRecipe.setIngredients(Arrays.asList("flour,sugar,milk"));
        existingRecipe.setInstructions(Arrays.asList("Mix ingredients","Bake at 350F", "Serve"));
        existingRecipe.setTags(Arrays.asList("Breakfast", "Cake", "Milk"));

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setName("Updated Pancake");
        updatedRecipe.setIngredients(Arrays.asList("flour,sugar,milk,maple syrup"));
        updatedRecipe.setInstructions(Arrays.asList("Mix ingredients","Bake at 350F", "Put Mapple Syrup","Serve"));
        updatedRecipe.setTags(Arrays.asList("Breakfast", "Cake", "Warm"));


        when(recipeRepository.findById(1)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(updatedRecipe);

        // Act
        ResponseEntity<?> response = recipeController.updateRecipe(1, updatedRecipe);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Recipe 'Updated Pancake' updated successfully!", response.getBody());
    }

    @Test
    void testUpdateRecipe_ShouldReturn404() {
        // Arrange
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setName("Updated Pancake");
        when(recipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = recipeController.updateRecipe(1, updatedRecipe);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteRecipe_ShouldReturn200() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Pancake");
        when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe));

        // Act
        ResponseEntity<?> response = recipeController.deleteRecipe(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Recipe 'Pancake' deleted successfully!", response.getBody());
    }

    @Test
    void testDeleteRecipe_ShouldReturn404() {
        // Arrange
        when(recipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = recipeController.deleteRecipe(1);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }
}
