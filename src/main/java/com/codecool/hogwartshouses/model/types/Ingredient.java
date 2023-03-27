package com.codecool.hogwartshouses.model.types;

import com.codecool.hogwartshouses.model.IngredientDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IngredientDeserializer.class)
public enum Ingredient {
    ANJELICA,
    CATERPILLAR,
    EYEBALL,
    SLOTH_BRAIN,
    WITCH_MUMMY,
    RAT_HAIR,
    UNICORN_HAIR,
    FAIRY_WINGS,
    ROSE_PETALS,
    MORNING_DEW,
    GINGER_ROOTS,
    FLUXWEED,
    KNOTGRASS,
    LACEWING_FLIES,
    LEECHES,
    HORN_OF_BICORN,
    BOOMSLANG_SKIN,
    PHILOSOPHERS_STONE;


    public String toNormalString(Ingredient ingredient) {
        return ingredient.toString().replaceAll("_", " ");
    }
}
