package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.SpellRepository;
import com.codecool.hogwartshouses.model.Spell;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SpellService {
    private final SpellRepository spellRepository;

    public SpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    public List<Spell> getAll() {
        return spellRepository.findAll();
    }

    public Spell getById(Long id) {
        return spellRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
