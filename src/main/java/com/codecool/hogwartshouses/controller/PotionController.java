package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.IngredientList;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.type.Ingredient;
import com.codecool.hogwartshouses.service.PotionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/potions")
public class PotionController {

    private final PotionService potionService;

    @Autowired
    public PotionController(PotionService potionService) {
        this.potionService = potionService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        return potionService.listAll();
    }

    @GetMapping("/{studentId}/")
    public List<Recipe> getAllRecipesByStudentId(@PathVariable long studentId){
        return potionService.getAllRecipesOfAStudent(studentId);
    }

    @PostMapping(path="/{studentId}", consumes = "application/json")
    public Recipe brewSomePotions(@PathVariable long studentId, @RequestBody IngredientList ingredientList){
        return potionService.brewSomePotion(studentId, ingredientList.getIngredientList());
    }
}
