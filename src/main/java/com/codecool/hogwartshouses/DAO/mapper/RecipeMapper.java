package com.codecool.hogwartshouses.DAO.mapper;

import com.codecool.hogwartshouses.model.Recipe;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecipeMapper implements RowMapper<Recipe> {

    @Override
    public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getLong("id"));
        recipe.setName(resultSet.getString("name"));
        return recipe;
    }
}
