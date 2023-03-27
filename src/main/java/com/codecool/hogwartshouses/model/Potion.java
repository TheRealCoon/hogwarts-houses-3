package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Potion {

    private long recipeID;
    private String name;
    private List<Ingredient> ingredients;
}
