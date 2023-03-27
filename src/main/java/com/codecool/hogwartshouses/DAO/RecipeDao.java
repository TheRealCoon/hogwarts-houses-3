package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {

    long addRecipeReturnID(String name);

    List<Recipe> getAllRecipes();

    Optional<Recipe> getRecipeByID(long id);

    void updateRecipe(Recipe recipe, long id);

    void deleteRecipe(long id);

    List<Recipe> getRecipesByStudentId(long id);

    void addRecipeToStudent(long studentId, long recipeId);
}
