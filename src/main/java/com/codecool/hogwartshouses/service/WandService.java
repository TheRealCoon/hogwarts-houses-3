package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.DAO.WandRepository;
import com.codecool.hogwartshouses.model.Wand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WandService {
    private WandRepository wandRepository;

    public WandService(WandRepository wandRepository) {
        this.wandRepository = wandRepository;
    }

    public List<Wand> getWands(){
        return wandRepository.findAll();
    }

    public Wand getWandById(Long id) {
        return wandRepository.findById(id).orElse(null);
    }
}
