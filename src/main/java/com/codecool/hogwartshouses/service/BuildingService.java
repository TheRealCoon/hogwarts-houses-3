package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.BuildingDAO;
import com.codecool.hogwartshouses.model.Building;
import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.types.HouseType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuildingService {

    private BuildingDAO buildingDAO;
    private RoomService roomService;

    public BuildingService(BuildingDAO buildingDAO, RoomService roomService) {
        this.buildingDAO = buildingDAO;
        this.roomService = roomService;
    }

    public void addBuilding(Building building) {
        buildingDAO.addBuilding(building);
    }

    public Set<Building> getAllBuilding() {
        Set<Building> buildings = buildingDAO.getAllBuilding();
        for (Building building : buildings) {
            Set<Room> rooms = roomService.getRoomByBuildingID(building.getId());
            building.setRooms(rooms);
        }
        return new TreeSet<>(buildings);
    }

    public Building getBuildingByID(long id) {
        Optional<Building> building = buildingDAO.getBuildingByID(id);
        if(building.isPresent()){
            Set<Room> rooms = roomService.getRoomByBuildingID(id);
            building.get().setRooms(rooms);
        }
        return building.orElse(null);
    }

    public void updateBuilding(Building building, long id){
        buildingDAO.updateBuilding(building, id);
    }

    public void deleteBuilding(long id){
        buildingDAO.deleteBuilding(id);
    }
}
