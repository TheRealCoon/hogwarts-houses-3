package com.codecool.hogwartshouses.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
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
    @OneToOne(mappedBy = "wand")
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "wand_spell",
            joinColumns = @JoinColumn(name = "wand_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id"))
    private Set<Spell> spells;

    @Override
    public String toString() {
        return String.format("%s's %s %s wand", teacher.getName(), color, woodType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wand wand)) return false;
        return Objects.equals(id, wand.id) &&
                Objects.equals(woodType, wand.woodType) &&
                Objects.equals(color, wand.color) &&
                Objects.equals(teacher, wand.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, woodType, color, teacher);
    }
}
