package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;


@Controller
@RequestMapping("/rooms")
public class RoomsController {
    private RoomService roomService;

    @Autowired
    public RoomsController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRoom(Model model) {
        Set<Room> rooms = roomService.getAllRoomWithStudents();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @GetMapping("/addroom")
    public String getAddRoomPage() {
        return "addroom";
    }

    @GetMapping("/findroom")
    public String getFindRoomPage(Model model) {
        Set<Room> rooms = roomService.getAllRoomWithStudents();
        model.addAttribute("rooms", rooms);
        return "findroom";
    }

    @PostMapping
    public String addRoom(@RequestParam("houseType") String houseType,
                          @RequestParam("places") int places,
                          @RequestParam("buildingId") long buildingId,
                          Model model) {
        Room newRoom = Room.builder()
                .houseType(HouseType.valueOf(houseType))
                .places(places)
                .buildingId(buildingId)
                .build();
        roomService.addRoom(newRoom);
        return getAllRoom(model);
    }

    @GetMapping("/{roomId}")
    public String getRoomByID(@PathVariable int roomId, Model model) {
        Set<Room> rooms = new TreeSet<>();
        Room room = roomService.getRoomByID(roomId);
        rooms.add(room);
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @DeleteMapping("/{roomId}")
    public String deleteRoom(@PathVariable int roomId, Model model) {
        roomService.deleteRoom(roomId);
        model.addAttribute("rooms", roomService.getAllRoomWithStudents());
        return "rooms";
    }

    //TODO update method with room body
    @PutMapping("/{roomId}")
    public String updateRoom(@PathVariable int roomId,
                             Model model) {
        roomService.updateRoom(roomId);
        //roomService.updateRoom(room,roomId);
        model.addAttribute("rooms", roomService.getAllRoomWithStudents());
        return "rooms";
    }

    @GetMapping("/available")
    public String getAvailableRooms(Model model) {
        Set<Room> availableRooms = roomService.getAvailableRooms();
        model.addAttribute("rooms", availableRooms);
        return "rooms";
    }

    @GetMapping("/rat-owners")
    public String getSafetyRoom(Model model) {
        Set<Room> safetyRooms = roomService.getSafetyRooms();
        model.addAttribute("rooms", safetyRooms);
        return "rooms";
    }

}
