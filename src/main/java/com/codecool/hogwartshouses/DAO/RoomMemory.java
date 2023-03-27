package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Room;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("mem")
public class RoomMemory implements RoomDAO {

    private Set<Room> rooms;
    private int nextID = 3;

    public RoomMemory(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Set<Room> getAllRoom() {
        return new TreeSet<>(rooms);
    }

    @Override
    public Set<Room> getAllRoomWithStudents() {
        return null;
    }

    @Override
    public List<Room> getRoomsByBuildingID(long id) {
        return null;
    }

    @Override
    public void updateRoom(Room room, long id) {
        for (Room r : rooms) {
            if(r.getId() == id){
                r.setPlaces(room.getPlaces());
                r.setHouseType(room.getHouseType());
                r.setMessy(room.isMessy());
            }
        }
    }

    @Override
    public void updateRoom(long id) {

    }

    @Override
    public void deleteRoom(long id) {
        rooms.removeIf(room -> room.getId() == id);
    }

    @Override
    public List<Room> getAvailableRooms() {
        return null;
    }

    @Override
    public Optional<Room> getRoomByID(long id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return Optional.of(room);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addRoom(Room room) {
        room.setId(nextID);
        this.nextID++;
        rooms.add(room);
    }
}
