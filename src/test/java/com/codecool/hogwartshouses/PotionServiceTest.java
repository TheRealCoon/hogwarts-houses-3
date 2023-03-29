package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.DAO.IngredientDaoJdbc;
import com.codecool.hogwartshouses.DAO.RecipeDaoJdbc;
import com.codecool.hogwartshouses.model.Potion;
import com.codecool.hogwartshouses.model.Recipe;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.Ingredient;
import com.codecool.hogwartshouses.model.types.PetType;
import com.codecool.hogwartshouses.service.PotionService;
import com.codecool.hogwartshouses.testmodels.TestIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PotionServiceTest {

    @Mock
    RecipeDaoJdbc recipeDao;

    @Mock
    IngredientDaoJdbc ingredientDao;

    @InjectMocks
    PotionService potionService;

    List<Recipe> recipes;
    Student student;
    List<TestIngredient> testIngredients;
    List<Ingredient> firstRecipeIngredients;
    List<Ingredient> secondRecipeIngredients;
    List<Potion> potions;
    Map<Long, Long> studentToRecipe;

    @BeforeEach
    void setup() {
        recipes = new ArrayList<>() {{
            add(new Recipe(1, "Polyjuice Potion"));
            add(new Recipe(2, "Felix Felicis"));

        }};
        student = new Student(1, "Harry Potter", HouseType.GRYFFINDOR, PetType.OWL);
        testIngredients = new ArrayList<>() {{
            add(new TestIngredient(Ingredient.FLUXWEED, 1));
            add(new TestIngredient(Ingredient.KNOTGRASS, 1));
            add(new TestIngredient(Ingredient.EYEBALL, 1));
            add(new TestIngredient(Ingredient.SLOTH_BRAIN, 1));
            add(new TestIngredient(Ingredient.WITCH_MUMMY, 1));
            add(new TestIngredient(Ingredient.HORN_OF_BICORN, 2));
            add(new TestIngredient(Ingredient.KNOTGRASS, 2));
            add(new TestIngredient(Ingredient.LEECHES, 2));
            add(new TestIngredient(Ingredient.RAT_HAIR, 2));
            add(new TestIngredient(Ingredient.CATERPILLAR, 2));
        }};

        firstRecipeIngredients = testIngredients.stream()
                .filter(i -> i.getRecipeId() == 1)
                .map(TestIngredient::getIngredientName)
                .toList();

        secondRecipeIngredients = testIngredients.stream()
                .filter(i -> i.getRecipeId() == 2)
                .map(TestIngredient::getIngredientName)
                .toList();

        potions = new ArrayList<>() {{
            add(new Potion(1, recipes.get(0).getName(), firstRecipeIngredients));
            add(new Potion(2, recipes.get(1).getName(), secondRecipeIngredients));
        }};

        studentToRecipe = new HashMap<>() {{
            put(1L, 1L);
        }};
    }

    private void setMocksGetAllPotions() {
        when(recipeDao.getAllRecipes()).thenReturn(recipes);
        when(ingredientDao.getAllIngredientsByRecipe(1)).thenReturn(potions.get(0).getIngredients());
        when(ingredientDao.getAllIngredientsByRecipe(2)).thenReturn(potions.get(1).getIngredients());
    }

    @Test
    public void shouldReturnEmptyPotionListWhenNoRecipeIsPresent() {
        when(recipeDao.getAllRecipes()).thenReturn(new ArrayList<>());
        potions = new ArrayList<>();
        assertEquals(potions, potionService.getAllPotions());
    }

    @Test
    public void shouldReturnAllPotionsWhenGetAllPotions() {
        setMocksGetAllPotions();
        assertEquals(potions, potionService.getAllPotions());
    }

    @Test
    public void shouldOnlyReturnRecipesOfGivenStudent() {
        long recipeId = studentToRecipe.get(student.getId());
        List<Recipe> expected = recipes.stream().filter(r -> r.getId() == recipeId).collect(Collectors.toList());

        when(recipeDao.getRecipesByStudentId(1)).thenReturn(expected);
        assertEquals(expected, potionService.getRecipesOfStudent(1));
    }
    @Test
    public void shouldReturnEmptyListWhenStudentIs() {
        Mockito.doThrow(new DataAccessException("There is no student with this id!"){}).when(recipeDao).getRecipesByStudentId(2);
        try {
            potionService.getRecipesOfStudent(2);
        } catch (RuntimeException e) {
            verify(potionService).getRecipesOfStudent(2);
            assertEquals("There is no student with this id!", e.getMessage());
        }

    }

    @Test
    public void givenExistingIngredientsOfRecipeReturnPotion() {
        setMocksGetAllPotions();
        long recipeId = studentToRecipe.get(student.getId());
        List<Recipe> expected = recipes.stream().filter(r -> r.getId() == recipeId).collect(Collectors.toList());
        when(recipeDao.getRecipesByStudentId(1)).thenReturn(expected);

        assertEquals(potions.get(0), potionService.brewPotion(1, firstRecipeIngredients));
    }

    @Test
    public void givenNonExistingIngredientsOfRecipeSaveIngredientsAndPotion() {
        List<Ingredient> newIngredients = new ArrayList<>() {{
            add(Ingredient.WITCH_MUMMY);
            add(Ingredient.ANJELICA);
            add(Ingredient.BOOMSLANG_SKIN);
            add(Ingredient.HORN_OF_BICORN);
            add(Ingredient.EYEBALL);
        }};

        try (MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);) {
            UUID uuid = new UUID(1, 2);
            uuidMockedStatic.when(UUID::randomUUID).thenReturn(uuid);

            when(recipeDao.addRecipeReturnID(anyString())).thenReturn(3L);

            setMocksGetAllPotions();
            long recipeId = studentToRecipe.get(student.getId());
            List<Recipe> expected = recipes.stream().filter(r -> r.getId() == recipeId).collect(Collectors.toList());
            when(recipeDao.getRecipesByStudentId(1)).thenReturn(expected);

            Potion newPotion = new Potion(3, uuid.toString(), newIngredients);

            Potion actualPotion = potionService.brewPotion(1, newIngredients);

            assertEquals(newPotion, actualPotion);
        }
    }
}
