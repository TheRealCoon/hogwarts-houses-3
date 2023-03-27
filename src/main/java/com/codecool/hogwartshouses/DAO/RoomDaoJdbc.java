package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.mapper.RoomMapper;
import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("jdbc")
public class RoomDaoJdbc implements RoomDAO {
    private JdbcTemplate template;
    private RoomMapper roomMapper;
    private Logger logger = LoggerFactory.getLogger(RoomDaoJdbc.class);

    private StudentService studentService;

    @Autowired
    public RoomDaoJdbc(JdbcTemplate template, RoomMapper roomMapper, StudentService studentService) {
        this.template = template;
        this.roomMapper = roomMapper;
        this.studentService = studentService;
    }

    @Override
    public Set<Room> getAllRoom() {
        String sql = "select * from room;";
        return new TreeSet<>(template.query(sql, roomMapper));
    }

    @Override
    public Set<Room> getAllRoomWithStudents() {
        String sql = "select distinct ro.id, ro.building_id, ro.house_type, ro.places, ro.is_messy from resident re " +
                "right join room ro on re.room_id = ro.id;";


        return new TreeSet<>(template.query(sql, (rs, rowNum) -> {
            Set<Student> students = studentService.getAllStudentByRoomID(rs.getLong(1));
            return Room.builder()
                    .id((int) rs.getLong(1))
                    .buildingId(rs.getLong(2))
                    .houseType(HouseType.valueOf(rs.getString(3)))
                    .places(rs.getInt(4))
                    .isMessy(rs.getBoolean(5))
                    .students(students)
                    .build();
        }));
    }

    @Override
    public List<Room> getRoomsByBuildingID(long id) {
        String sql = "select * from room where building_id = ?;";
        return template.query(sql, roomMapper,id);
    }

    @Override
    public Optional<Room> getRoomByID(long id) {
        String sql = "select * from room where id = ?;";
        Room room = null;
        try {
            room = template.queryForObject(sql,roomMapper,id);
        }catch (DataAccessException e){
            logger.info("Room not found with this id: " + id);
        }
        return Optional.ofNullable(room);
    }

    @Override
    public void addRoom(Room room) {
        String sql = "INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (?,?,?);";
        int insert = template.update(sql,room.getBuildingId(), room.getHouseType().toString(), room.getPlaces());
        if(insert == 1)
            logger.info("New room inserted");
    }

    @Override
    public void updateRoom(Room room, long id) {
        String sql = "update room set house_type = ?, places = ?, is_messy = ? where id = ?;";
        int updated = template.update(sql,room.getHouseType().toString(), room.getPlaces(), room.checkIfMessy(), id);
        if(updated == 1)
            logger.info("Room updated! id: " + id);
        else
            logger.info("There is no room with this id");
    }
    @Override
    public void updateRoom(long id) {
        String sql = "update room set is_messy = ? where id = ?;";
        int updated = template.update(sql,"false", id);
        if(updated == 1)
            logger.info("Room updated! id: " + id);
        else
            logger.info("There is no room with this id");
    }

    @Override
    public void deleteRoom(long id) {
        String sql = "delete from resident where room_id = ?;" +
                "delete from room where id = ?;";

        int deleted = template.update(sql, id, id);
        if(deleted == 1)
            logger.info("Room deleted! id: " + id);
    }

    @Override
    public List<Room> getAvailableRooms() {
        String sql = "select ro.id, ro.building_id, ro.house_type, ro.places, ro.is_messy, count(re.student_id) " +
                "from resident re join room ro on re.room_id = ro.id " +
                "group by ro.id " +
                "having count(re.student_id) < ro.places;";
        return template.query(sql, roomMapper);
    }

}
