package com.codecool.hogwartshouses.data_sample;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.PetType;
import com.codecool.hogwartshouses.DAO.RoomMemory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
@Getter
public class RoomCreator {

    @Autowired
    RoomMemory roomMemory;

    public void initialize() {
        Set<Room> initedRooms = new TreeSet<>();

        Set<Student> firstRoomStudents = new TreeSet<>();
        Student test1 = Student.builder().name("Hermione Granger").petType(PetType.CAT).houseType(HouseType.GRYFFINDOR).build();
        Student test2 = Student.builder().name("Parvati Patil").petType(PetType.OWL).houseType(HouseType.GRYFFINDOR).build();
        firstRoomStudents.add(test1);
        firstRoomStudents.add(test2);
        //Room firstRoom = Room.builder().id(1).houseType(HouseType.GRYFFINDOR).places(2).isMessy(true).students(firstRoomStudents).build();
       // initedRooms.add(firstRoom);

        Set<Student> secondRoomStudents = new TreeSet<>();
        Student test3 = Student.builder().name("Draco Malfoy").petType(PetType.RAT).houseType(HouseType.SLYTHERIN).build();
        Student test4 = Student.builder().name("Gregory Goyle").petType(PetType.OWL).houseType(HouseType.SLYTHERIN).build();
        secondRoomStudents.add(test3);
        secondRoomStudents.add(test4);
       // Room secondRoom = Room.builder().id(2).houseType(HouseType.SLYTHERIN).places(4).students(secondRoomStudents).isMessy(true).build();
       // initedRooms.add(secondRoom);

        this.roomMemory = new RoomMemory(initedRooms);
    }

    public RoomCreator() {
        initialize();
    }

}
