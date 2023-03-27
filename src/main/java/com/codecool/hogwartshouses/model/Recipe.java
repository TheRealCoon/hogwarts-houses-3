package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.type.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Recipe {

    @JsonIgnore
    long id;

    String name;

    List<Ingredient> ingredients;

    public Recipe( String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
