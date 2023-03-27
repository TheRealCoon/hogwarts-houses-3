package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.mapper.StudentMapper;
import com.codecool.hogwartshouses.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;


@Repository
public class StudentDaoJdbc implements StudentDAO {

    private JdbcTemplate template;
    private StudentMapper studentMapper;
    private Logger logger = LoggerFactory.getLogger(StudentDaoJdbc.class);

    @Autowired
    public StudentDaoJdbc(JdbcTemplate template, StudentMapper studentMapper) {
        this.template = template;
        this.studentMapper = studentMapper;
    }

    @Override
    public Set<Student> getAllStudent() {
        String sql = "select * from student;";
        return new TreeSet<> (template.query(sql, studentMapper));
    }

    @Override
    public Set<Student> getAllStudentByRoomID(long id) {
        String sql = "select s.id, s.name, s.house_type, s.pet_type from resident r " +
                "join student s on r.student_id = s.id " +
                "where r.room_id = ?;";
        return new TreeSet<> (template.query(sql,studentMapper,id));
    }

    @Override
    public Optional<Student> getStudentByID(long id) {
        String sql = "select * from student where id = ?;";
        Student student = null;
        try {
            student = template.queryForObject(sql,studentMapper,id);
        }catch (DataAccessException e){
            logger.info("Student not found with this id: " + id);
        }
        return Optional.ofNullable(student);
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values (?, ?, ?);";
        int insert = template.update(sql,student.getName(), student.getHouseType().toString(), student.getPetType().toString());
        if(insert == 1)
            logger.info("New student inserted");
    }

    @Override
    public void updateStudent(Student student, long id) {
        String sql = "update student set name = ?, house_type = ?, pet_type = ? where id = ?;";
        int updated = template.update(sql, student.getName(), student.getHouseType().toString(), student.getPetType().toString(), id);
        if(updated == 1)
            logger.info("Student updated");
        else
            logger.info("There is no student with this id");
    }

    @Override
    public void deleteStudent(long id) {
        String sql = "delete from studentrecipes where student_id = ?;" +
                "delete from resident where student_id = ?;" +
                "delete from student where id = ?;";
        int deleted = template.update(sql, id, id, id);
        if(deleted == 1)
            logger.info("Student deleted! id: " + id);
    }

    @Override
    public void addStudentToRoom(long studentId, long roomId) {
        String sql = "insert into resident(student_id, room_id) values(?,?);";
        int insert = template.update(sql,studentId,roomId);
        if(insert == 1)
            logger.info("Student added to room");
    }
}
