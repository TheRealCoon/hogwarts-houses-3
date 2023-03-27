package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.Ingredient;
import com.codecool.hogwartshouses.model.types.PetType;
import com.codecool.hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudent(Model model){
        Set<Student> students = studentService.getAllStudent();
        model.addAttribute("students",students);
        return "students";
    }

    @GetMapping("/roomId/{roomId}")
    public Set<Student> getAllStudentByRoom(@PathVariable long roomId){
        return studentService.getAllStudentByRoomID(roomId);
    }

    @GetMapping("/{studentId}")
    public String getStudentByID(@PathVariable long studentId,
                                 Model model){
        Set<Student> students = studentService.getStudentByID(studentId);
        model.addAttribute("students",students);
        return "students";
    }

    @PostMapping
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("houseType") String houseType,
                             @RequestParam("petType") String petType,
                             Model model){
        Student student = Student.builder()
                .name(name)
                .houseType(HouseType.valueOf(houseType))
                .petType(PetType.valueOf(petType))
                .build();

        studentService.addStudent(student);
        Set<Student> students = studentService.getAllStudent();
        model.addAttribute("students",students);
        return "students";
    }

    @PutMapping
    public String updateStudentPage(@RequestBody Student student,
                                    Model model){
        model.addAttribute("student", student);
        return "update_student";
    }

    @PutMapping("/{studentId}")
    public String updateStudent(@PathVariable("studentId") long studentId,
                                @RequestBody Student student,
                                Model model){
        studentService.updateStudent(student,studentId);
        return getAllStudent(model);
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable long studentId,
                                Model model){
        studentService.deleteStudent(studentId);
        Set<Student> students = studentService.getAllStudent();
        model.addAttribute("students",students);
        return "students";
    }

    @PostMapping("/{studentId}/addToRoom/{roomId}")
    public String addStudentToRoom(@PathVariable("studentId") long studentId,
                                 @PathVariable("roomId") long roomId){
        studentService.addStudentToRoom(studentId,roomId);
        return "redirect:/rooms";
    }

    @GetMapping("/brewing/{studentId}/{studentName}")
    public String brewingPage(@PathVariable("studentId") long studentId,
                              @PathVariable("studentName") String studentName,
                              Model model){

        model.addAttribute("studentId", studentId);
        model.addAttribute("studentName", studentName);
        model.addAttribute("ingredients", Arrays.asList(Ingredient.values()));
        return "brewing";
    }
}
