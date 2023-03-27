package com.codecool.hogwartshouses.DAO.mapper;

import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.type.HouseType;
import com.codecool.hogwartshouses.model.type.PetType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setHouseType(HouseType.valueOf(resultSet.getString("house_type")));
        student.setPetType(PetType.valueOf(resultSet.getString("pet_type")));
        return student;
    }
}
