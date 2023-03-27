package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Building;

import java.util.Optional;
import java.util.Set;

public interface BuildingDAO {

    void addBuilding(Building building);

    Set<Building> getAllBuilding();

    Optional<Building> getBuildingByID(long id);

    void updateBuilding(Building building, long id);

    void deleteBuilding(long id);

}
