package com.codecool.hogwartshouses.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellDTO {
    private Long id;
    private String name;
    private String usage;
    private Integer power;
    private boolean isBanned;
    private Set<Long> wandIds;
}
