package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t join t.wand w where w.woodType = :woodType")
    List<Teacher> findTeacherByWand(@Param("woodType") String woodType);
}
