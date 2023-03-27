package com.codecool.hogwartshouses.DAO.mapper;

import com.codecool.hogwartshouses.model.Building;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuildingMapper implements RowMapper<Building> {

    @Override
    public Building mapRow(ResultSet resultSet, int i) throws SQLException {
        Building building = new Building();
        building.setId(resultSet.getLong("id"));
        return building;
    }
}
