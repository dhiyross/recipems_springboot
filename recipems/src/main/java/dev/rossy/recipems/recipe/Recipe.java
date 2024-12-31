package dev.rossy.recipems.recipe;

//import  dev.rossy.recipe.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "recipe")
@Data // Lombok untuk getter, setter, toString, equals, dan hashCode
@NoArgsConstructor
public class Recipe {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotBlank
        private String name;

        @Min(1)
        private Integer prep_time_minutes;

        @Min(1)
        private Integer cook_time_minutes;

        @Min(1)
        private Integer servings;

        private String difficulty;

        private String cuisine;

        @Min(0)
        private Integer calories_per_serving;

        @Min(0)
        private Double price; // Harga per porsi

        private String user_id;

        private String image;

        private Double rating;

        private Integer review_count;

        private Long transaction_id;

//        private List<String> meal_type;

        private String meal_type;

        // Simpan ingredients, instructions, dan tags sebagai String
        private String ingredients;   // Disimpan sebagai "garam,gula,tepung"
        private String instructions;  // Disimpan sebagai "1. Panaskan oven;2. Masukkan tepung"
        private String tags;          // Disimpan sebagai "dessert,quick,easy"

        // Konversi List<String> ke String untuk menyimpan
        public void setIngredients(List<String> ingredients) {
                this.ingredients = String.join(",", ingredients);
        }

        public List<String> getIngredients() {
                return Arrays.asList(this.ingredients.split(","));
        }

        public void setInstructions(List<String> instructions) {
                this.instructions = String.join(";", instructions);
        }

        public List<String> getInstructions() {
                return Arrays.asList(this.instructions.split(";"));
        }
        public void setTags(List<String> tags) {
                this.tags = String.join(",", tags);
        }

        public List<String> getTags() {
                return Arrays.asList(this.tags.split(","));
        }
}
