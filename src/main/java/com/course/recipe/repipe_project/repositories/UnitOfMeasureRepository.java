package com.course.recipe.repipe_project.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.course.recipe.repipe_project.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
