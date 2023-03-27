package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.DAO.mapper.BuildingMapper;
import com.codecool.hogwartshouses.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuildingDaoJdbcImpl implements BuildingDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BuildingMapper mapper;

    @Autowired
    public BuildingDaoJdbcImpl(JdbcTemplate jdbcTemplate, BuildingMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Building> findAll() {
        String sql = "SELECT * FROM BUILDING ";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Building findById(long id) {
        String sql = "SELECT * FROM BUILDING WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public void add(Building building) {
        String sql = "INSERT INTO BUILDING (NUMBER_OF_ROOMS) VALUES ( ? ) ";
        jdbcTemplate.update(sql, building.getNumberOfRooms());
    }

    @Override
    public void update(long id, Building building) {
        String sql = "UPDATE BUILDING SET NUMBER_OF_ROOMS = ? WHERE ID = ?";
        jdbcTemplate.update(sql, building.getNumberOfRooms(), id);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM BUILDING WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<String> getAllPictureNameByBuildingId(long id) {
        String sql = "SELECT NAME FROM PICTURE";
        return jdbcTemplate.queryForList(sql, String.class);
    }


}
