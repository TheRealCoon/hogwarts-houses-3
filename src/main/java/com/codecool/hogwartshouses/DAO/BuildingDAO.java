package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Building;

import java.util.List;

public interface BuildingDAO {

    List<Building> findAll();

    Building findById(long id);

    void add(Building building);

    void update(long id, Building building);

    void delete(long id);

    List<String> getAllPictureNameByBuildingId(long id);
}
