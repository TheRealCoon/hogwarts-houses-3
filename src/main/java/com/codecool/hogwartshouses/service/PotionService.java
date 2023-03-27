package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.IngredientDAO;
import com.codecool.hogwartshouses.DAO.RecipeDao;
import com.codecool.hogwartshouses.model.Potion;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.types.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PotionService {

    RecipeDao recipeDao;

    IngredientDAO ingredientDAO;

    @Autowired
    public PotionService(RecipeDao recipeDao, IngredientDAO ingredientDAO) {
        this.recipeDao = recipeDao;
        this.ingredientDAO = ingredientDAO;
    }

    public List<Potion> getAllPotions() {
        List<Recipe> recipes = recipeDao.getAllRecipes();
        List<Potion> potions = new ArrayList<>();
        if (recipes.size() > 0) {
            for (Recipe recipe : recipes) {
                Potion p = new Potion();
                p.setRecipeID(recipe.getId());
                p.setName(recipe.getName());
                p.setIngredients(ingredientDAO.getAllIngredientsByRecipe(recipe.getId()));
                potions.add(p);
            }
        }
        return potions;
    }

    public Potion brewPotion(long studentId, List<Ingredient> ingredients) {
        Optional<Potion> potion = findPotionByIngredients(ingredients);
        long recipeId = potion.map(Potion::getRecipeID).orElseGet(() -> savePotionAndReturnRecipeId(ingredients));
        Optional<Recipe> recipe = getRecipesOfStudent(studentId)
                .stream()
                .filter(r -> r.getId() == recipeId)
                .findFirst();
        if (recipe.isEmpty()) {
            recipeDao.addRecipeToStudent(studentId, recipeId);
        }
        return potion.orElseGet(() -> new Potion(recipeId, UUID.randomUUID().toString(), ingredients));
    }


    private long savePotionAndReturnRecipeId(List<Ingredient> ingredients) {
        String randomName = UUID.randomUUID().toString();
        long id = recipeDao.addRecipeReturnID(randomName);
        addIngredientsOfRecipe(ingredients, id);
        return id;
    }

    private void addIngredientsOfRecipe(List<Ingredient> ingredients, long recipeId) {
        ingredients.forEach(i -> ingredientDAO.addIngredient(i, recipeId));
    }

    private Optional<Potion> findPotionByIngredients(List<Ingredient> ingredients) {
        return getAllPotions().stream()
                .filter(p -> new HashSet<>(p.getIngredients()).containsAll(ingredients))
                .findFirst();
    }

    public List<Recipe> getRecipesOfStudent(long id) {
        try {
            return recipeDao.getRecipesByStudentId(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
