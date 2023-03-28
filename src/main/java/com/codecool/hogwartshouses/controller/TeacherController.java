package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Teacher;
import com.codecool.hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }

    @GetMapping("/teachers/{id}")
    public Teacher getTeacherById(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }
}
