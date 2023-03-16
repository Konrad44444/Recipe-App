package com.course.recipe.repipe_project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.course.recipe.repipe_project.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    
}