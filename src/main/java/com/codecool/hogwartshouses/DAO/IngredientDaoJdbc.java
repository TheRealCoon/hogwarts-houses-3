package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.mapper.IngredientMapper;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.types.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@Slf4j
public class IngredientDaoJdbc implements IngredientDAO{

    JdbcTemplate template;

    IngredientMapper mapper;

    @Autowired
    public IngredientDaoJdbc(JdbcTemplate template, IngredientMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void addIngredient(Ingredient ingredient, long recipeID) {
        String sql = "insert into ingredients (name, recipe_id) values (?,?);";
        int insert = template.update(sql,ingredient.toString(),recipeID);
        if (insert == 1)
            log.info("New ingredient inserted");
    }

    @Override
    public List<Ingredient> getAllIngredientsByRecipe(long id) {
        String sql = "select * from ingredients where recipe_id = ?";
        return template.query(sql, mapper, id);
    }

    @Override
    public Optional<Ingredient> getIngredientById(long id) {
        String sql = "select name from ingredients where id = ?;";
        return Optional.of(template.query(sql, mapper, id).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Potion not found with this id: " + id)));
    }

    @Override
    public void updateIngredient(Ingredient ingredient, long id) {
        String sql = "update ingredients set " +
                "name = ? " +
                "where id = ?";

        template.update(sql,
                ingredient.toString(),
                id);
    }

    @Override
    public void deleteIngredient(long id) {
        String sql = "delete from ingredients where id = ?";
        template.update(sql, id);
    }
}
