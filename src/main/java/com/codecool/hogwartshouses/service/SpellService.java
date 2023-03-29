package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.SpellRepository;
import com.codecool.hogwartshouses.mapper.SpellMapper;
import com.codecool.hogwartshouses.model.SpellDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpellService {
    private final SpellRepository spellRepository;
    private final SpellMapper mapper;

    public SpellService(SpellRepository spellRepository, SpellMapper mapper) {
        this.spellRepository = spellRepository;
        this.mapper = mapper;
    }

    public List<SpellDTO> getAll() {
        return spellRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SpellDTO> getById(Long id) {
        return spellRepository.findById(id)
                .stream()
                .map(mapper::toDTO)
                .findFirst();
    }
}
