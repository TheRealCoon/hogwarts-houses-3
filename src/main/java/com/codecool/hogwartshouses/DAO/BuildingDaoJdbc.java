package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.mapper.BuildingMapper;
import com.codecool.hogwartshouses.model.Building;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Component
public class BuildingDaoJdbc implements BuildingDAO {

    private JdbcTemplate jdbcTemplate;

    private BuildingMapper buildingMapper;
    private Logger logger = LoggerFactory.getLogger(BuildingDaoJdbc.class);

    public BuildingDaoJdbc(JdbcTemplate jdbcTemplate, BuildingMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.buildingMapper = mapper;
    }

    @Override
    public void addBuilding(Building building) {
        String sql = "insert into building(name) values (?);";
        jdbcTemplate.update(sql, building.getName());
    }

    @Override
    public Set<Building> getAllBuilding() {
        String sql = "select * from building;";
        return new TreeSet<>(jdbcTemplate.query(sql, buildingMapper));
    }

    @Override
    public Optional<Building> getBuildingByID(long id) {
        String sql = "select * from building where id = ?;";
        Building building = null;
        try {
            building = jdbcTemplate.queryForObject(sql, buildingMapper, id);
        }catch (DataAccessException e){
            logger.info("Building not found with this id: " + id);
        }
        return Optional.ofNullable(building);
    }

    @Override
    public void updateBuilding(Building building, long id) {
        String sql = "update building " +
                "set name = ? " +
                "where id = ?";

        int updated = jdbcTemplate.update(sql, building.getName(), id);
        if (updated == 1)
            logger.info("Building updated: " + id);
        else
            logger.info("There is no building with this id");
    }

    @Override
    public void deleteBuilding(long id) {
        String sql = "delete from building where id = ?";
        int deleted = jdbcTemplate.update(sql, id);
        if(deleted == 1)
            logger.info("Building deleted: " + id);
        else
            logger.info("There is no building with this id");
    }

}
