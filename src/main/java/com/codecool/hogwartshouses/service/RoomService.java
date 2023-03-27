package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.RoomDAO;
import com.codecool.hogwartshouses.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDAO roomDAO;

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    public void saveNewRoom(Room room) {
        roomDAO.saveNewRoom(room);
    }

    public void deleteRoom(Long roomId) {
        roomDAO.deleteRoom(roomId);
    }

    public Room findById(Long id) {
        return roomDAO.findById(id);
    }

    public void updateRoom(Long id, Room room) {
        roomDAO.updateRoom(id, room);
    }

    public List<Room> findAvailableRooms() {
        return roomDAO.findAvailableRooms();
    }

    public List<Room> findRoomWithNoCatOrOwl() {
        return roomDAO.findRoomWithNoCatOrOwl();
    }
}
