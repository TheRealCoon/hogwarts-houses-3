package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.TeacherRepository;
import com.codecool.hogwartshouses.mapper.TeacherMapper;
import com.codecool.hogwartshouses.model.Teacher;
import com.codecool.hogwartshouses.model.TeacherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private TeacherMapper mapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper mapper) {
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    public List<TeacherDTO> getTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> mapper.toDto(teacher))
                .toList();
    }

    public TeacherDTO getTeacherById(Long id) {
        return mapper.toDto(teacherRepository.findById(id).orElse(null));
    }

    public List<TeacherDTO> findTeacherByWandWoodType(String woodType) {
        return teacherRepository.findTeacherByWand(woodType).stream()
                .map(teacher -> mapper.toDto(teacher))
                .toList();
    }
}
