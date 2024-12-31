package dev.rossy.recipems.recipe;

import java.util.List;

public class RecipeResponseDTO {

    private Integer id;
    private String name;
    private String cuisine;
    private Integer servings;
    private Double price;
    private Double rating;
    private Integer prep_time_minutes;
    private Integer cook_time_minutes;
    private String difficulty;
    private Double calories_per_serving;
    private String user_id;
    private String image;
    private Integer review_count;
    private String meal_type;

    private List<String> ingredients;
    private List<String> instructions;
    private List<String> tags;

    // Getter dan Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public Integer getServings() { return servings; }
    public void setServings(Integer servings) { this.servings = servings; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getPrep_time_minutes() { return prep_time_minutes; }
    public void setPrep_time_minutes(Integer prepTimeMinutes) { this.prep_time_minutes = prepTimeMinutes; }

    public Integer getCook_time_minutes() { return cook_time_minutes; }
    public void setCook_time_minutes(Integer cook_time_minutes) { this.cook_time_minutes = cook_time_minutes; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Double getCalories_per_serving() { return calories_per_serving; }
    public void setCalories_per_serving(Double calories_per_serving) { this.calories_per_serving = calories_per_serving; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Integer getReview_count() { return review_count; }
    public void setReview_count(Integer review_count) { this.review_count = review_count; }

    public String getMeal_type() { return meal_type; }
    public void setMeal_type(String meal_type) { this.meal_type = meal_type; }

    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }

    public List<String> getInstructions() { return instructions; }
    public void setInstructions(List<String> instructions) { this.instructions = instructions; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}

