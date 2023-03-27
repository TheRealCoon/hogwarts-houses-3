package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.Building;
import com.codecool.hogwartshouses.model.types.HouseType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuildingMapper implements RowMapper<Building> {

    @Override
    public Building mapRow(ResultSet rs, int i) throws SQLException {
        return Building.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .build();
    }
}
