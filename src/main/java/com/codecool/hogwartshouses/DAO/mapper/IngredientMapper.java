package com.codecool.hogwartshouses.DAO.mapper;

import com.codecool.hogwartshouses.model.type.Ingredient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
        return Ingredient.valueOf(resultSet.getString("ingredient_name"));
    }
}
