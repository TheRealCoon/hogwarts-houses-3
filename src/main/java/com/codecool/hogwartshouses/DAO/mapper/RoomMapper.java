package com.codecool.hogwartshouses.DAO.mapper;

import com.codecool.hogwartshouses.model.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setRoomNumber(resultSet.getInt("room_number"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setNumberOfBeds(resultSet.getInt("number_of_beds"));
        room.setHasEmptyBed(resultSet.getBoolean("has_empty_beds"));
        return room;
    }
}
