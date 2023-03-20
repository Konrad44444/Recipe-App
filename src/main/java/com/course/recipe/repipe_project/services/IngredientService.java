package com.course.recipe.repipe_project.services;

import com.course.recipe.repipe_project.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
