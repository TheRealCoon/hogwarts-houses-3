package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.types.HouseType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int i) throws SQLException {
        return Room.builder()
                .id((int)rs.getLong("id"))
                .houseType(HouseType.valueOf(rs.getString("house_type")))
                .places(rs.getInt("places"))
                .isMessy(rs.getBoolean("is_messy"))
                .build();
    }
}
