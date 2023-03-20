package com.course.recipe.repipe_project.services;

import java.util.Set;

import com.course.recipe.repipe_project.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
