package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AvailableRoomsController {

    @Autowired
    private RoomService service;

    @GetMapping("/available-rooms")
    @ResponseBody
    public List<Room> findAvailableRooms() {
        return service.findAvailableRooms();
    }
}
