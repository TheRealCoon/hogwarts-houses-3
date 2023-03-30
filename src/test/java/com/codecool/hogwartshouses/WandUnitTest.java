package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.controller.WandController;
import com.codecool.hogwartshouses.model.WandDTO;
import com.codecool.hogwartshouses.service.WandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class WandUnitTest {

    @Mock
    private WandService wandService;

    @InjectMocks
    private WandController wandController;

    WandDTO wand1;
    WandDTO wand2;
    List<WandDTO> wands;


    @BeforeEach
    void setup() {
        wand1 = new WandDTO(1L, "oak", "black", 1L, Set.of(1L, 2L, 3L));
        wand2 = new WandDTO(1L, "pine", "white", 2L, Set.of(1L, 2L, 3L));
        wands = List.of(wand1, wand2);
    }
    @Test
    public void shouldReturnEmptyWandList()  {
        when(wandService.getWands()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), wandController.getWands());
    }

    @Test
    public void shouldReturnAllWands(){
        when(wandService.getWands()).thenReturn(wands);
        assertEquals(wands, wandController.getWands());
    }

    @Test
    public void shouldReturnAWandByGivenId(){
        when(wandService.getWandById(2L))
                .thenReturn(Optional.of(wand2));
        WandDTO expectedWand = wands.get(1);
        assertEquals(HttpStatus.OK, wandController.getWandById(2L).getStatusCode());
        assertEquals(expectedWand, wandController.getWandById(2L).getBody());
    }

    @Test
    public void shouldReturnNotFoundResponseWhenFindWandWithWrongId(){
        when(wandService.getWandById(10L))
                .thenReturn(Optional.empty());
        assertEquals(HttpStatus.NOT_FOUND, wandController.getWandById(10L).getStatusCode());
    }
}
