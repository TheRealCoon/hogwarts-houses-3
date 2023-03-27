package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.types.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientDAO {

    void addIngredient(Ingredient ingredient, long recipeID);

    List<Ingredient> getAllIngredientsByRecipe(long id);

    Optional<Ingredient> getIngredientById(long id);

    void updateIngredient(Ingredient ingredient, long id);

    void deleteIngredient(long id);
}
