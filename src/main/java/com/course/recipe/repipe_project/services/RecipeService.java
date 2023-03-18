package com.course.recipe.repipe_project.services;

import java.util.List;

import com.course.recipe.repipe_project.domain.Recipe;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe findById(Long id);
}
