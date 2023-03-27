package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.DAO.mapper.StudentMapper;
import com.codecool.hogwartshouses.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoJdbcImpl implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;
    private final StudentMapper mapper;

    @Autowired
    public StudentDaoJdbcImpl(JdbcTemplate jdbcTemplate, StudentMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM STUDENT";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Student findById(long id) {
        String sql = "SELECT * FROM STUDENT WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO STUDENT (NAME, HOUSE_TYPE, PET_TYPE) VALUES ( ?,?,? ) ";
        jdbcTemplate.update(sql, student.getName(), student.getHouseType(), student.getPetType());
    }

    @Override
    public void update(long id, Student student) {
        String sql = "UPDATE STUDENT SET NAME = ?, HOUSE_TYPE = ?, PET_TYPE = ? WHERE ID=?";
        jdbcTemplate.update(sql, student.getName(), student.getHouseType(), student.getPetType(), id);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM STUDENT WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Student> findAllResidentOfARoomByRoomId(long roomId) {
        String sql = "SELECT STUDENT.ID, NAME, HOUSE_TYPE, PET_TYPE " +
                "FROM STUDENT " +
                "INNER JOIN RESIDENT R on STUDENT.ID = R.STUDENT_ID " +
                "WHERE ROOM_ID = ?";
        return jdbcTemplate.query(sql, mapper, roomId);
    }
}
