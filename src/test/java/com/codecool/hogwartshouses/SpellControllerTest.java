package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.controller.SpellController;
import com.codecool.hogwartshouses.model.SpellDTO;
import com.codecool.hogwartshouses.service.SpellService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SpellControllerTest {
    @Mock
    private SpellService spellService;

    @InjectMocks
    private SpellController spellController;

    List<SpellDTO> spells;

    @BeforeEach
    public void init() {
        spells = List.of(
                new SpellDTO(1L, "test_spell1", "usage1", 10, true, Set.of(1L, 2L)),
                new SpellDTO(2L, "test_spell2", "usage2", 50, false, Set.of(1L))
        );
    }

    @Test
    public void getAllSpells_calledOnEmptyDataBase_returnsEmptyList() {
        Mockito.when(spellService.getAll()).thenReturn(new ArrayList<>());
        spells = new ArrayList<>();
        assertEquals(spells, spellController.getAllSpells());
    }

    @Test
    public void getAllSpells_calledOnPopulatedDataBase_returnsListOfAllSpells() {
        Mockito.when(spellService.getAll()).thenReturn(spells);
        assertEquals(spells, spellController.getAllSpells());
    }

    @Test
    public void getSpellByID_existingId_returnsCorrectSpellAndStatus200() {
        final long id = 1L;
        Optional<SpellDTO> maybeSpellDTO = spells.stream().filter(s -> s.getId() == id).findFirst();
        Mockito.when(spellService.getById(id)).thenReturn(maybeSpellDTO);
        assertEquals(new ResponseEntity<>(maybeSpellDTO.orElse(null), HttpStatus.OK), spellController.getSpellById(id));
    }

    @Test
    public void getSpellByID_nonExistingId_returnsCorrectSpellAndStatus404() {
        final long id = 10L;
        Optional<SpellDTO> maybeSpellDTO = spells.stream().filter(s -> s.getId() == id).findFirst();
        Mockito.when(spellService.getById(id)).thenReturn(maybeSpellDTO);
        assertEquals(new ResponseEntity<>(maybeSpellDTO.orElse(null), HttpStatus.NOT_FOUND), spellController.getSpellById(id));
    }
}
