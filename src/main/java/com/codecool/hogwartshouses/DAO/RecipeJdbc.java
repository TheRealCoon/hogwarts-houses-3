package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.DAO.mapper.IngredientMapper;
import com.codecool.hogwartshouses.DAO.mapper.RecipeMapper;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.type.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeJdbc {


    private final JdbcTemplate jdbcTemplate;
    private final RecipeMapper mapper;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public RecipeJdbc(JdbcTemplate jdbcTemplate, RecipeMapper recipeMapper, IngredientMapper ingredientMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = recipeMapper;
        this.ingredientMapper = ingredientMapper;
    }

    public List<Recipe> listAll() {
        String sql = "SELECT * FROM RECIPE";
        return jdbcTemplate.query(sql, mapper);
    }

    public List<Ingredient> getAllIngredientsOfARecipeByRecipeId(long recipeId) {
        String sql = "SELECT INGREDIENT_NAME FROM RECIPE_INGREDIENT WHERE RECIPE_ID = ?";
        return jdbcTemplate.query(sql, ingredientMapper, recipeId);
    }

    public List<Recipe> getAllRecipesOfAStudent(long studentId) {
        String sql = "SELECT RECIPE.ID, RECIPE.NAME " +
                "FROM RECIPE " +
                "INNER JOIN STUDENT_RECIPE SR on RECIPE.ID = SR.RECIPE_ID " +
                "WHERE STUDENT_ID = ?";
        return jdbcTemplate.query(sql, mapper, studentId);
    }

    public void addRecipe(Recipe recipe) {
        String sql = "INSERT INTO RECIPE (NAME) VALUES ( ? )";
        jdbcTemplate.update(sql, recipe.getName());
    }

    public void addRecipeToStudent(long studentId, long recipeId) {
        String sql = "INSERT INTO STUDENT_RECIPE (STUDENT_ID, RECIPE_ID) VALUES ( ?, ? )";
        jdbcTemplate.update(sql, studentId, recipeId);
    }

    public void saveRecipeToStudent(long studentId, Recipe recipe) {
        addRecipe(recipe);
        long recipeId = listAll()
                .stream()
                .filter(r -> r.getIngredients()
                .containsAll(recipe.getIngredients()))
                .findFirst()
                .get()
                .getId();
        addRecipeToStudent(studentId, recipeId);
    }
}
