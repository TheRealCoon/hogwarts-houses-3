package com.codecool.hogwartshouses.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WANDS")
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

    @Override
    public String toString() {
        return String.format("%s's %s %s wand", teacher.getName(), color, woodType);
    }
}
