package com.codecool.hogwartshouses.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WandDTO {
    private Long id;
    private String woodType;
    private String color;
    private Long teacherId;
    private Set<Long> spellIds;
}
