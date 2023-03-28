package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.Teacher;
import com.codecool.hogwartshouses.model.TeacherDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDTO toDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        return TeacherDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .subject(teacher.getSubject())
                .isWitch(teacher.getIsWitch())
                .age(teacher.getAge())
                .wandId(teacher.getWand().getId())
                .build();

    }
}
