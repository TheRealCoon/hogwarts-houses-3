package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.SpellDTO;
import com.codecool.hogwartshouses.service.SpellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/spells")
@RestController
public class SpellController {
    private final SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GetMapping
    public List<SpellDTO> getAllSpells() {
        return spellService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellDTO> getSpellById(@PathVariable Long id) {
        return spellService.getById(id).map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
