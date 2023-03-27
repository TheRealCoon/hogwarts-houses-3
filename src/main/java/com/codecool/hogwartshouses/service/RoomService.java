package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.RoomDAO;
import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomDAO roomDAO;
    private StudentService studentService;

    @Autowired
    public RoomService(@Qualifier("jdbc") RoomDAO roomDAO, StudentService studentService){
        this.roomDAO = roomDAO;
        this.studentService = studentService;
    }

    public Set<Room> getAllRoom() {
        return new HashSet<>(roomDAO.getAllRoom());
    }

    public Set<Room> getAllRoomWithStudents() {
        return new TreeSet<>(roomDAO.getAllRoomWithStudents());
    }

    public Set<Room> getRoomByBuildingID(long id){
        List<Room> rooms = roomDAO.getRoomsByBuildingID(id);
        for (Room room : rooms) {
            Set<Student> students = studentService.getAllStudentByRoomID(room.getId());
            room.setStudents(students);
        }
        return new TreeSet<>(rooms);
    }

    public Room getRoomByID(long id) {
        Optional<Room> room = roomDAO.getRoomByID(id);
        if(room.isPresent()){
            Set<Student> students = studentService.getAllStudentByRoomID(id);
            room.get().setStudents(students);
        }
        return room.orElse(null);
    }

    public void updateRoom(Room room,long id) {
        roomDAO.updateRoom(room, id);
    }
    public void updateRoom(long id) {
        roomDAO.updateRoom(id);
    }

    public void deleteRoom(long id) {
        roomDAO.deleteRoom(id);
    }

    public void addRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public Set<Room> getAvailableRooms() {
        List<Room> availableRooms = roomDAO.getAvailableRooms();
        for (Room availableRoom : availableRooms) {
            Set<Student> students = studentService.getAllStudentByRoomID(availableRoom.getId());
            availableRoom.setStudents(students);
        }
        return new TreeSet<>(availableRooms);
    }

    public Set<Room> getSafetyRooms() {
        Set<Room> allRoom = roomDAO.getAllRoomWithStudents();
        return allRoom.stream()
                .filter(Room::isSafetyRoom)
                .collect(Collectors.toSet());
    }
}
