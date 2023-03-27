package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student findById(long id);

    void add(Student student);

    void update(long id, Student student);

    void delete(long id);

    List<Student> findAllResidentOfARoomByRoomId(long roomId);
}
