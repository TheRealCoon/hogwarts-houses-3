package com.codecool.hogwartshouses.model;


import com.codecool.hogwartshouses.model.types.HouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Building implements Comparable<Building> {
    private long id;
    private String name;
    private Set<Room> rooms;

    @Override
    public int compareTo(Building o) {
        return (int) (this.getId()-o.getId());
    }
}
