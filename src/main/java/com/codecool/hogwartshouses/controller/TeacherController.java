package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.TeacherDTO;
import com.codecool.hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<TeacherDTO> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id).map(teacherDTO -> new ResponseEntity<>(teacherDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/wand")
    public List<TeacherDTO> findTeacherByWandWoodType(@RequestParam("wand") String woodType) {
        return teacherService.findTeacherByWandWoodType(woodType);
    }
}
