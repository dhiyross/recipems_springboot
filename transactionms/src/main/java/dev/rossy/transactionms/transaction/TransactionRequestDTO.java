package dev.rossy.transactionms.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransactionRequestDTO {

    @NotNull
    private Integer recipeId;

    @Min(1)
    private Integer quantity;

    @NotNull
    private Integer customerId; // Tambahan untuk Customer ID

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}