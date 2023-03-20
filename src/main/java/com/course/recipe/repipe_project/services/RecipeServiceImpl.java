package com.course.recipe.repipe_project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.recipe.repipe_project.commands.IngredientCommand;
import com.course.recipe.repipe_project.commands.RecipeCommand;
import com.course.recipe.repipe_project.converters.RecipeCommandToRecipe;
import com.course.recipe.repipe_project.converters.RecipeToRecipeCommand;
import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j //logger
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }
    
    @Override
    public List<Recipe> getRecipes() {
        log.debug("Recipe Service");

        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }
    
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeID: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);        
    }
     
}
