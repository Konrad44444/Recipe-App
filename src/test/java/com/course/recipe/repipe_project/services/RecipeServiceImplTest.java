package com.course.recipe.repipe_project.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.repositories.RecipeRepository;    
    
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception{
        //to test services
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }
        
    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        ArrayList<Recipe> recipeData = new ArrayList<>();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);


        List<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll(); //recipe repository was called only once [used by recipeService.getRecipes()]
    }
}
    