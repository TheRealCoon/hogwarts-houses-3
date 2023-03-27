package com.codecool.hogwartshouses.testmodels;

import com.codecool.hogwartshouses.model.types.Ingredient;

public class TestIngredient {

    private Ingredient testIngredientName;

    private long recipeId;

    public TestIngredient(Ingredient testIngredientName, long recipeId) {
        this.testIngredientName = testIngredientName;
        this.recipeId = recipeId;
    }

    public Ingredient getIngredientName() {
        return testIngredientName;
    }

    public void setIngredientName(Ingredient testIngredientName) {
        this.testIngredientName = testIngredientName;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
