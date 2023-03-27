package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> findAll();
    List<Room> findAvailableRooms();
    Room findById(Long id);
    void saveNewRoom(Room room);
    void deleteRoom(Long roomId);
    void updateRoom(Long id, Room room);
    List<Room> findRoomWithNoCatOrOwl();
    List<Room> findAllRoomByBuildingId(long buildingId);
}
