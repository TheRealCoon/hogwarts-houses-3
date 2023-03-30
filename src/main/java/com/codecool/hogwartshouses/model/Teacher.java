package com.codecool.hogwartshouses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TEACHERS")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;
    private Boolean isWitch;
    private int age;
    @OneToOne
    @JoinColumn(name = "wand_id")
    private Wand wand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        return age == teacher.age &&
                Objects.equals(id, teacher.id) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(subject, teacher.subject) &&
                Objects.equals(isWitch, teacher.isWitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subject, isWitch, age);
    }
}
