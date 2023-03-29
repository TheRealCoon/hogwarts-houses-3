package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.Spell;
import com.codecool.hogwartshouses.model.SpellDTO;
import com.codecool.hogwartshouses.model.Wand;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SpellMapper {
    public SpellDTO toDTO(Spell spell) {
        if (spell == null) {
            return null;
        }
        return SpellDTO.builder()
                .id(spell.getId())
                .name(spell.getName())
                .usage(spell.getUsage())
                .power(spell.getPower())
                .isBanned(spell.isBanned())
                .wandIds(spell.getWands().stream().map(Wand::getId).collect(Collectors.toSet()))
                .build();
    }

}
