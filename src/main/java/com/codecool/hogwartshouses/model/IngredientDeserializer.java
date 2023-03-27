package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.Ingredient;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class IngredientDeserializer extends JsonDeserializer<Ingredient> {
    @Override
    public Ingredient deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Ingredient.valueOf(jsonParser.getValueAsString().toUpperCase());
    }
}
