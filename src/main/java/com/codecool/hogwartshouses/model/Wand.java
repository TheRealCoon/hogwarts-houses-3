package com.codecool.hogwartshouses.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Wand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String woodType;
    private String color;
    @OneToOne
    private Teacher teacher;
    @ManyToMany
    private Set<Spell> spells;


}
