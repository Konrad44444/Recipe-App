package com.course.recipe.repipe_project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;


    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }
    
}
