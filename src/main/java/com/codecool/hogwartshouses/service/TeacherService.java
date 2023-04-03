package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.TeacherRepository;
import com.codecool.hogwartshouses.mapper.TeacherMapper;
import com.codecool.hogwartshouses.model.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<TeacherDTO> getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .stream()
                .map(mapper::toDto)
                .findFirst();
    }

    public List<TeacherDTO> findTeacherByWandWoodType(String woodType) {
        return teacherRepository.findAllByWand_WoodType(woodType).stream()
                .map(teacher -> mapper.toDto(teacher))
                .toList();
    }
}
