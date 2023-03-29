package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.Spell;
import com.codecool.hogwartshouses.model.WandDTO;
import com.codecool.hogwartshouses.model.Wand;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class WandMapper {
    public WandDTO toWandDTO(Wand wand){
        if (wand==null) return null;
        return WandDTO.builder()
                .id(wand.getId())
                .color(wand.getColor())
                .woodType(wand.getWoodType())
                .teacherId(wand.getTeacher().getId())
                .spellIds(wand.getSpells().stream().map(Spell::getId).collect(Collectors.toSet()))
                .build();
    }
}
