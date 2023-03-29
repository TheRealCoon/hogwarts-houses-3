package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.WandRepository;
import com.codecool.hogwartshouses.mapper.WandMapper;
import com.codecool.hogwartshouses.model.WandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WandService {
    private WandRepository wandRepository;
    private WandMapper wandMapper;

    public WandService(WandRepository wandRepository, WandMapper wandMapper) {
        this.wandRepository = wandRepository;
        this.wandMapper = wandMapper;
    }

    public List<WandDTO> getWands(){

        return wandRepository.findAll()
                .stream()
                .map(wand -> wandMapper.toWandDTO(wand))
                .toList();
    }

    public WandDTO getWandById(Long id) {
        return wandMapper.toWandDTO(wandRepository.findById(id).orElse(null));
    }
}
