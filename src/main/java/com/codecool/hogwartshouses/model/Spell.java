package com.codecool.hogwartshouses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SPELLS")
@ToString(onlyExplicitlyIncluded = true)
public class Spell {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;
    @ToString.Include
    private String name;
    private String usage;
    private Integer power;
    private boolean isBanned;
    @ManyToMany(mappedBy = "spells")
    private Set<Wand> wands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spell spell)) return false;
        return isBanned == spell.isBanned &&
                Objects.equals(id, spell.id) &&
                Objects.equals(name, spell.name) &&
                Objects.equals(usage, spell.usage) &&
                Objects.equals(power, spell.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, usage, power, isBanned);
    }
}
