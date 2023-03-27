package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoomForRatOwnersController {

    @Autowired
    private RoomService service;

    @GetMapping("/rooms/rat-owners")
    @ResponseBody
    public List<Room> findRoomWithNoCatOrOwl() {
        return service.findRoomWithNoCatOrOwl();
    }
}
