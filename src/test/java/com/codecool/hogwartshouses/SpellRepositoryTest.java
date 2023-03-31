package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.DAO.SpellRepository;
import com.codecool.hogwartshouses.model.Spell;
import com.codecool.hogwartshouses.model.Teacher;
import com.codecool.hogwartshouses.model.Wand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@SqlGroup({
        @Sql(scripts = "/test_schema.sql",
                config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql("/test_data.sql")})
public class SpellRepositoryTest {

    @Autowired
    private SpellRepository spellRepository;

    private List<Spell> spells;

    @BeforeEach
    public void initializeListFields() {
        Teacher teacher1 = new Teacher(1L, "teacher name1", "subject1", true, 30, null);
        Teacher teacher2 = new Teacher(2L, "teacher name2", "subject2", false, 100, null);
        Wand wand1 = new Wand(1L, "wand type1", "black", teacher1, null);
        Wand wand2 = new Wand(2L, "wand type2", "white", teacher2, null);
        teacher1.setWand(wand1);
        teacher2.setWand(wand2);
        Spell spell1 = new Spell(1L, "test_spell1", "usage1", 10, true, Set.of(wand1, wand2));
        Spell spell2 = new Spell(2L, "test_spell2", "usage2", 50, false, Set.of(wand1));
        wand1.setSpells(Set.of(spell1, spell2));
        wand2.setSpells(Set.of(spell1));
        spells = List.of(spell1, spell2);
    }

    @Test
    public void findAll_returnsAllSpellsAsList() {
        assertEquals(spells, spellRepository.findAll());
    }

    @Test
    public void findById_existingID_returnsCorrectSpell() {
        Spell spell = spells.get(0);
        assertEquals(spell, spellRepository.findById(1L).orElse(null));
    }

    @Test
    public void findById_nonExistingID_returnsNull() {
        assertNull(spellRepository.findById(34L).orElse(null));
    }

}
