package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Building;
import com.codecool.hogwartshouses.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public String getAllBuilding(Model model){
        Set<Building> buildings = buildingService.getAllBuilding();
        model.addAttribute("buildings", buildings);
        return "buildings";
    }

    @GetMapping("/{buildingId}")
    public String getBuildingByID(@PathVariable long buildingId,
                                  Model model){
        Set<Building> building = new HashSet<>(){{
            add(buildingService.getBuildingByID(buildingId));
        }};
        model.addAttribute("buildings", building);
        return "buildings";
    }

    @PostMapping
    public String addBuilding(@RequestParam("name") String name,
                              Model model){
        Building building = Building.builder().name(name).build();
        buildingService.addBuilding(building);
        return getAllBuilding(model);
    }

    @PutMapping
    public String updateBuildingPage(@RequestBody Building building,
                                     Model model){
        model.addAttribute("building", building);
        return "update_building";
    }

    @PutMapping("/{buildingId}")
    public String updateBuilding(@PathVariable long buildingId,
                                 @RequestBody Building building,
                                 Model model){
        buildingService.updateBuilding(building,buildingId);
        return getAllBuilding(model);
    }

    @DeleteMapping("/{buildingId}")
    public String deleteBuilding(@PathVariable long buildingId,
                                 Model model){
        buildingService.deleteBuilding(buildingId);
        return getAllBuilding(model);
    }
}
