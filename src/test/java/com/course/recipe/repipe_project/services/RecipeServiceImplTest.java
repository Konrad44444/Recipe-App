package com.course.recipe.repipe_project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.function.Executable;

import com.course.recipe.repipe_project.converters.RecipeCommandToRecipe;
import com.course.recipe.repipe_project.converters.RecipeToRecipeCommand;
import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.exceptions.NotFoundException;
import com.course.recipe.repipe_project.repositories.RecipeRepository;    
    
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void setUp() throws Exception{
        //to test services
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned, "Null recipe returned");

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
        
    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();
        ArrayList<Recipe> recipeData = new ArrayList<>();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);


        List<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());

        verify(recipeRepository, times(1)).findAll(); //recipe repository was called only once [used by recipeService.getRecipes()]
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void getRecipeByIdNotFound() throws Exception {
        Assertions.assertThrows(NotFoundException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                Optional<Recipe> recipeOptional = Optional.empty();

                when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

                Recipe recipeReturned = recipeService.findById(1L);
    
                //should go boom
            }
        });
        
    }
}
    