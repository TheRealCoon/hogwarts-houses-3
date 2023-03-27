package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.RecipeJdbc;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.type.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PotionService {

    private final RecipeJdbc recipeJdbc;

    @Autowired
    public PotionService(RecipeJdbc recipeJdbc) {
        this.recipeJdbc = recipeJdbc;
    }

    private List<Recipe> setIngredientsOfRecipes(List<Recipe> recipes){
        recipes.forEach(recipe ->
                recipe.setIngredients(recipeJdbc.getAllIngredientsOfARecipeByRecipeId(recipe.getId())));
        return recipes;
    }

    public List<Recipe> listAll(){
        return setIngredientsOfRecipes(recipeJdbc.listAll());
    }

    public List<Recipe> getAllRecipesOfAStudent(long studentId) {
        return recipeJdbc.getAllRecipesOfAStudent(studentId);
    }

    public Recipe brewSomePotion(long studentId, List<Ingredient> ingredients) {
        Optional<Recipe> recipe = maybeRecipe(ingredients);
        if (recipe.isPresent()){
            saveRecipeToStudent(studentId, recipe.get());

        } else{
            Recipe newRecipe = new Recipe(UUID.randomUUID().toString(), ingredients);
            saveRecipeToStudent(studentId, newRecipe);
        }
        return getAllRecipesOfAStudent(studentId).stream()
                .filter(recipe1 -> recipe1.getIngredients().containsAll(ingredients))
                .findFirst()
                .get();
    }

    private Optional<Recipe> maybeRecipe(List<Ingredient> ingredients) {
        List<Recipe> allRecipes = listAll();
        return allRecipes
                .stream()
                .filter(recipe -> recipe.getIngredients()
                        .containsAll(ingredients))
                .findFirst();
    }

    public void saveRecipeToStudent(long studentId, Recipe recipe){
        recipeJdbc.saveRecipeToStudent(studentId, recipe);
    }
}
