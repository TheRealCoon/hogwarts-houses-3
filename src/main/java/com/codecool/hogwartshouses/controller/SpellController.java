package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.service.SpellService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/spells")
@Controller
public class SpellController {
    private final SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GetMapping
    public String getAllSpells(Model model) {
        model.addAttribute("spells", spellService.getAll());
        return "spells";
    }

    @GetMapping("/{id}")
    public String getSpellById(Model model, @PathVariable Long id) {
        model.addAttribute("spells", List.of(spellService.getById(id)));
        return "spells";
    }

}
