package com.codecool.hogwartshouses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Spell {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String usage;
    private Integer power;
    private boolean isBanned;
    @ManyToMany
    //TODO set up many-to-many
    private Set<Wand> wands;
}
