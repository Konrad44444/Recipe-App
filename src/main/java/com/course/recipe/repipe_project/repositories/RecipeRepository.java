package com.course.recipe.repipe_project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.course.recipe.repipe_project.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    
}
