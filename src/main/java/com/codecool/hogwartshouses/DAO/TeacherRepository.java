package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByWand_WoodType(String woodType);
}
