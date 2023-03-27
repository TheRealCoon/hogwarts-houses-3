package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.StudentDAO;
import com.codecool.hogwartshouses.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class StudentService {

    private StudentDAO studentDAO;


    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Set<Student> getAllStudent(){
        Set<Student> students = studentDAO.getAllStudent();
        return new TreeSet<>(students);
    }

    public Set<Student> getAllStudentByRoomID(long id){
        Set<Student> students = studentDAO.getAllStudentByRoomID(id);
        return new TreeSet<>(students);
    }

    public Set<Student> getStudentByID(long id){
        Set<Student> result = new HashSet<>();
        if(studentDAO.getStudentByID(id).isPresent())
            result.add(studentDAO.getStudentByID(id).get());
        return result;
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student, long id) {
        studentDAO.updateStudent(student, id);
    }

    public void deleteStudent(long id) {
        studentDAO.deleteStudent(id);
    }

    public void addStudentToRoom(long studentId, long roomId) {
        studentDAO.addStudentToRoom(studentId, roomId);
    }
}
