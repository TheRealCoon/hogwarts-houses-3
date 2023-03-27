package com.codecool.hogwartshouses.controller;


import com.codecool.hogwartshouses.model.Potion;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.types.Ingredient;
import com.codecool.hogwartshouses.service.PotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/potions")
public class PotionController {
    private PotionService potionService;


    public PotionController(PotionService potionService) {
        this.potionService = potionService;
    }


    @GetMapping
    public String listPotions(Model model) {
        List<Potion> potions = potionService.getAllPotions();
        model.addAttribute("potions", potions);
        return "potions";
    }

    @PostMapping("/{studentId}")
    public String brewPotion(@PathVariable("studentId") long studentId,
                             @RequestParam("i1") Ingredient i1,
                             @RequestParam("i2") Ingredient i2,
                             @RequestParam("i3") Ingredient i3,
                             @RequestParam("i4") Ingredient i4,
                             @RequestParam("i5") Ingredient i5,
                             Model model) {
        potionService.brewPotion(studentId, List.of(i1, i2, i3, i4, i5));
        return listPotions(model);
    }

    @GetMapping("/{studentId}")
    public String getRecipesOfStudent(@PathVariable("studentId") Long student_id,
                                            Model model) {
        List<Recipe> recipes = potionService.getRecipesOfStudent(student_id);
        model.addAttribute("recipes", recipes);
        return "recipes";

    }
}
