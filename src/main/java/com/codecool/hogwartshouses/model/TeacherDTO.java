package com.codecool.hogwartshouses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherDTO {
    private Long id;
    private String name;
    private String subject;
    private Boolean isWitch;
    private int age;
    private long wandId;
}
