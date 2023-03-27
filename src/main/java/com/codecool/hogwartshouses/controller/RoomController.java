package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return service.findAll();
    }

    @GetMapping("/rooms/{roomId}")
    public Room getRoomById(@PathVariable("roomId") Long roomId) {
        return service.findById(roomId);
    }

    @PostMapping("/rooms")
    public void saveNewRoom(@RequestBody Room room) {
        service.saveNewRoom(room);
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@PathVariable(name = "roomId") long roomId, HttpServletResponse response) {
        service.deleteRoom(roomId);
    }

    @PutMapping("/rooms/{roomId}")
    public void updateRoom(@PathVariable(name="roomId") long roomId, @RequestBody Room room) {
        service.updateRoom(roomId, room);
    }
}
