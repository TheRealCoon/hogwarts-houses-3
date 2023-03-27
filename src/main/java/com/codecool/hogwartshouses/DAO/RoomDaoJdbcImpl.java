package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.DAO.mapper.RoomMapper;
import com.codecool.hogwartshouses.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDaoJdbcImpl implements RoomDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RoomMapper mapper;

    @Autowired
    public RoomDaoJdbcImpl(JdbcTemplate jdbcTemplate, RoomMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }


    @Override
    public List<Room> findAll() {
        String sql = "SELECT ID ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS, BUILDING_ID " +
                "FROM ROOM R";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Room> findAvailableRooms() {
        String sql = "SELECT DISTINCT ROOM.ID, ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS" +
                " FROM ROOM " +
                "JOIN RESIDENT RES ON ROOM.ID = RES.ROOM_ID " +
                "WHERE HAS_EMPTY_BEDS IS TRUE";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Room findById(Long id) {
        String sql = "SELECT DISTINCT ID, ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS, BUILDING_ID" +
                " FROM ROOM " +
                "WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public void saveNewRoom(Room room) {
        long building_id = 0;
        String sql = "INSERT INTO ROOM(ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS, BUILDING_ID) " +
                "VALUES ( ?,?,?,?,? )";
        jdbcTemplate.update(sql,
                room.getRoomNumber(), room.getCapacity(), room.getNumberOfBeds(), room.getHasEmptyBed(), building_id);
    }

    @Override
    public void deleteRoom(Long roomId) {
        String sql = "DELETE FROM ROOM WHERE ID = ?";
        jdbcTemplate.update(sql, roomId);
    }

    @Override
    public void updateRoom(Long id, Room room) {
        String sql = "UPDATE ROOM SET ROOM_NUMBER = ?, CAPACITY = ?, NUMBER_OF_BEDS = ?, HAS_EMPTY_BEDS = ? WHERE ID = ?";
        jdbcTemplate.update(sql, room.getRoomNumber(), room.getCapacity(), room.getNumberOfBeds(), room.getHasEmptyBed(), id);
    }

    @Override
    public List<Room> findRoomWithNoCatOrOwl() {
        String sql = "SELECT DISTINCT R.ID, R.ROOM_NUMBER, R.CAPACITY, R.NUMBER_OF_BEDS, R.HAS_EMPTY_BEDS, R.BUILDING_ID" +
                " FROM ROOM R " +
                "JOIN RESIDENT RES ON R.ID = RES.ROOM_ID " +
                "JOIN STUDENT S on RES.STUDENT_ID = S.ID " +
                "WHERE S.PET_TYPE NOT IN ('CAT', 'OWL')";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Room> findAllRoomByBuildingId(long buildingId) {
        String sql = "SELECT DISTINCT ID, ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS, BUILDING_ID" +
                " FROM ROOM " +
                "WHERE BUILDING_ID = ?";
        return jdbcTemplate.query(sql, mapper, buildingId);
    }

}
