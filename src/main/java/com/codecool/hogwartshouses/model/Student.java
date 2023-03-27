package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.PetType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Getter
public class Student implements Comparable<Student>{
    private long id;
    private String name;
    private HouseType houseType;
    private PetType petType;

    public Student(long id, String name, HouseType houseType, PetType petType) {
        this.id = id;
        this.name = name;
        this.houseType = houseType;
        this.petType = petType;
    }

    @Override
    public int compareTo(Student o) {
        return (int) (this.getId()-o.getId());
    }
}
