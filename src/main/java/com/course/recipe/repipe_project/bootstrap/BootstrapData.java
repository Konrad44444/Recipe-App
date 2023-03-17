package com.course.recipe.repipe_project.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.course.recipe.repipe_project.domain.Category;
import com.course.recipe.repipe_project.domain.Difficulty;
import com.course.recipe.repipe_project.domain.Ingredient;
import com.course.recipe.repipe_project.domain.Notes;
import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.domain.UnitOfMeasure;
import com.course.recipe.repipe_project.repositories.CategoryRepository;
import com.course.recipe.repipe_project.repositories.RecipeRepository;
import com.course.recipe.repipe_project.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent>{
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public BootstrapData(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");
        
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");
        
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found!");

        UnitOfMeasure each = eachOptional.get();
        UnitOfMeasure tablespoon = tablespoonOptional.get();
        UnitOfMeasure teaspoon = teaspoonOptional.get();
        UnitOfMeasure cup = cupOptional.get();
        UnitOfMeasure pinch = pinchOptional.get();
        UnitOfMeasure ounce = ounceOptional.get();  

        //get categories
        Optional<Category> americanCategory = categoryRepository.findByDescription("American");
        if(!americanCategory.isPresent()) throw new RuntimeException("Expectem Category Not Found!");

        Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");
        if(!americanCategory.isPresent()) throw new RuntimeException("Expectem Category Not Found!");

        Category american = americanCategory.get();
        Category mexican = mexicanCategory.get();

        //guacamole
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole!");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1. Cut the avocados\n2. Mash the avocado flesh\n3. Add the remaining ingredients to taste\n 4. Serve immediately");

        Notes guacamoleNote = new Notes();
        guacamoleNote.setNote("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        
        //refactor - set note in one method
        guacamoleNote.setRecipe(guacamole);
        guacamole.setNotes(guacamoleNote);

        //refactor - addIngredient() method adding recipe to ingredient immedietly
        guacamole.getIngredients().add(new Ingredient("ripe avodacos", new BigDecimal("2"), each, guacamole));
        guacamole.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal("0.5"), teaspoon, guacamole));
        guacamole.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal("2"), tablespoon, guacamole));
        guacamole.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal("0.5"), teaspoon, guacamole));

        guacamole.getCategories().add(mexican);

        recipes.add(guacamole);

        //tacos
        Recipe tacos = new Recipe();

        tacos.setDescription("Cheese Tacos!");
        tacos.setPrepTime(5);
        tacos.setCookTime(5);
        tacos.setServings(4);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setDirections("1. Heat the pan, prep the ingredients\n2. Butter a tortilla and heat it in the pan until it bubbles\n3. Add cheese\n 4. Flip half of the tortilla over the cheese side\n5. Remove from pan, add extras");

        Notes tacoNote = new Notes();
        tacoNote.setNote("This method makes delicious, buttery, cheesey tacos. If you are avoiding butter, or frying oil (which you could also use), you could make these in a microwave. Soften the tortillas first in the microwave. We use 20 seconds on high per tortilla, with the tortillas sitting on a paper towel in the microwave to absorb moisture. Once they've been softened this way you can add cheese and fold them over and heat them a few seconds more, just until the cheese melts.");
        tacoNote.setRecipe(tacos);

        tacos.setNotes(tacoNote);

        tacos.getIngredients().add(new Ingredient("corn tortillas", new BigDecimal("1"), cup, tacos));
        tacos.getIngredients().add(new Ingredient("butten", new BigDecimal("1"), tablespoon, tacos));
        tacos.getIngredients().add(new Ingredient("cheddar cheese", new BigDecimal("1"), ounce, tacos));
        tacos.getIngredients().add(new Ingredient("salsa", new BigDecimal("1"), cup, tacos));

        tacos.getCategories().add(american);

        recipes.add(tacos);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Bootstrap");
        recipeRepository.saveAll(getRecipes());
    }

    
}
