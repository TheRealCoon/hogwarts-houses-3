package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.controller.TeacherController;
import com.codecool.hogwartshouses.model.TeacherDTO;
import com.codecool.hogwartshouses.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TeacherUnitTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    List<TeacherDTO> teachers;

    @BeforeEach
    void init(){
        this.teachers = new ArrayList<>(){{
            add(new TeacherDTO(1L,"name1", "subject1", true, 30,1));
            add(new TeacherDTO(2L,"name2", "subject2", true, 40,2));
            add(new TeacherDTO(3L,"name3", "subject3", true, 3,1));
            add(new TeacherDTO(4L,"name4", "subject4", true, 666,2));
        }};
    }

    @Test
    public void shouldReturnEmptyTeacherList(){
        Mockito.when(teacherService.getTeachers()).thenReturn(new ArrayList<>());
        teachers = new ArrayList<>();
        Assertions.assertEquals(teachers, teacherController.getTeachers());
    }

    @Test
    public void shouldReturnAllTeachers(){
        Mockito.when(teacherService.getTeachers()).thenReturn(teachers);
        Assertions.assertEquals(teachers, teacherController.getTeachers());
    }

    @Test
    public void shouldReturnATeacherByGivenId(){
        Mockito.when(teacherService.getTeacherById(2L))
                .thenReturn(Optional.of(new TeacherDTO(2L,"name2", "subject2", true, 40,2)));
        TeacherDTO expectedTeacher = teachers.get(1);
        Assertions.assertEquals(HttpStatus.OK, teacherController.getTeacherById(2L).getStatusCode());
        Assertions.assertEquals(expectedTeacher.getId(), teacherController.getTeacherById(2L).getBody().getId());
    }

    @Test
    public void shouldReturnNotFoundResponseWhenFindTeacherWithWrongId(){
        Mockito.when(teacherService.getTeacherById(10L))
                .thenReturn(Optional.empty());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, teacherController.getTeacherById(10L).getStatusCode());
    }

}
