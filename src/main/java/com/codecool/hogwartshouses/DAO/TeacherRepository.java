package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
