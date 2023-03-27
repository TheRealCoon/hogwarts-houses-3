package com.codecool.hogwartshouses.model;

public class Room {

    private Long id;

    private Integer roomNumber;

    private Integer capacity;

    private Integer numberOfBeds;

    private Boolean hasEmptyBed;

//    private List<Student> residents;

    public Room() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Boolean getHasEmptyBed() {
        return hasEmptyBed;
    }

    public void setHasEmptyBed(Boolean hasEmptyBed) {
        this.hasEmptyBed = hasEmptyBed;
    }

//    public List<Student> getResidents() {
//        return residents;
//    }

//    public void setResidents(List<Student> residents) {
//        this.residents = residents;
//    }
}
