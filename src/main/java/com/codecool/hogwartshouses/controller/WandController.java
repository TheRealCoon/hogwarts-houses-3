package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.WandDTO;
import com.codecool.hogwartshouses.service.WandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wands")
public class WandController {
    private WandService wandService;

    public WandController(WandService wandService) {
        this.wandService = wandService;
    }

    @GetMapping
    public List<WandDTO> getWands(){
        return wandService.getWands();
    }

    @GetMapping("/{id}")
    public WandDTO getWandById(@PathVariable Long id){
        return wandService.getWandById(id);
    }
}
