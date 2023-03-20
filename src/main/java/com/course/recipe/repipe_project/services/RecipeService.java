package com.course.recipe.repipe_project.services;

import java.util.List;

import com.course.recipe.repipe_project.commands.RecipeCommand;
import com.course.recipe.repipe_project.domain.Recipe;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
