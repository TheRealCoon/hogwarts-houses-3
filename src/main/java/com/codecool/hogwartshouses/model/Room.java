package com.codecool.hogwartshouses.model;


import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.PetType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Getter
public class Room implements Comparable<Room>{
    private int id;
    private long buildingId;
    private Set<Student> students;
    private HouseType houseType;
    private int places;
    private boolean isMessy;

    public Room(int id, long buildingId, Set<Student> students, HouseType houseType, int places, boolean isMessy) {
        this.id = id;
        this.buildingId = buildingId;
        this.students = students;
        this.houseType = houseType;
        this.places = places;
        this.isMessy = isMessy;
    }
    public boolean checkIfMessy(){
        return this.isMessy;
    }
    public boolean isSafetyRoom(){
        if(students.isEmpty())
            return true;
        for (Student student : students) {
            if(student.getPetType() == PetType.CAT || student.getPetType() == PetType.OWL)
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(Room o) {
        return this.getId()-o.getId();
    }

}
