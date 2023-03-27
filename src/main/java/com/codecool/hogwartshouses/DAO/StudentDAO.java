package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Student;

import java.util.Optional;
import java.util.Set;

public interface StudentDAO {
    Set<Student> getAllStudent();
    Set<Student> getAllStudentByRoomID(long id);
    Optional<Student> getStudentByID(long id);
    void addStudent(Student student);
    void updateStudent(Student student, long id);
    void deleteStudent(long id);
    void addStudentToRoom(long studentId, long roomId);
}
