package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.mapper.RecipeMapper;
import com.codecool.hogwartshouses.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class RecipeDaoJdbc implements RecipeDao {

    JdbcTemplate jdbcTemplate;

    RecipeMapper mapper;

    @Autowired
    public RecipeDaoJdbc(JdbcTemplate jdbcTemplate, RecipeMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public long addRecipeReturnID(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into recipe (name) values (?);";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        String sql = "select * from recipe;";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Optional<Recipe> getRecipeByID(long id) {
        String sql = "select * from recipe where id = ?;";
        return Optional.of(jdbcTemplate.query(sql, mapper, id).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Recipe not found with this id: " + id)));
    }

    @Override
    public void updateRecipe(Recipe recipe, long id) {
        String sql = "update recipe set name = ? where id = ?;";

        int updated = jdbcTemplate.update(sql,
                recipe.getName(), id);
        if (updated == 1)
            log.info("Recipe updated");
        else
            log.info("There is no recipe with this id");
    }

    @Override
    public void deleteRecipe(long id) {
        String sql = "delete from recipe where id = ?;";
        int deleted = jdbcTemplate.update(sql, id);
        if (deleted == 1)
            log.info("Recipe deleted! id: " + id);
    }

    public List<Recipe> getRecipesByStudentId(long id) {
        String sql = "select r.id, r.name from recipe r " +
                "inner join studentrecipes sr on r.id = sr.recipe_id " +
                "where sr.student_id = ?;";
        try {
            return jdbcTemplate.query(sql, mapper, id);

        } catch (DataAccessException e) {
            log.info("Student not found with this id: " + id);
        }

        return null;
    }

    public void addRecipeToStudent(long studentId, long recipeId) {
        String sql = "insert into studentrecipes (student_id, recipe_id) values (?, ?);";
        jdbcTemplate.update(sql, studentId, recipeId);
    }

}
