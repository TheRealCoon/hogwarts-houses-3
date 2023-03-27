package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.type.HouseType;
import com.codecool.hogwartshouses.model.type.PetType;

public class Student {

    private Long id;

    private String name;

    private HouseType houseType;

    private PetType petType;

//    @JsonBackReference
    private Room room;


    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
