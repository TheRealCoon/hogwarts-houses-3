package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomDAO {
    Set<Room> getAllRoom();
    Set<Room> getAllRoomWithStudents();
    List<Room> getRoomsByBuildingID(long id);
    Optional<Room> getRoomByID(long id);
    void addRoom(Room room);
    void updateRoom(Room room, long id);
    void updateRoom(long id);
    void deleteRoom(long id);
    List<Room> getAvailableRooms();
}
