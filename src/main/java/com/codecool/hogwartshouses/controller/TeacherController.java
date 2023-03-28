package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.TeacherDTO;
import com.codecool.hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public List<TeacherDTO> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO getTeacherById(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/wand")
    public List<TeacherDTO> findTeacherByWandWoodType(@RequestParam("wand") String woodType) {
        return teacherService.findTeacherByWandWoodType(woodType);
    }
}
