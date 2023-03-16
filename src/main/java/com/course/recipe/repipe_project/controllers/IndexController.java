package com.course.recipe.repipe_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.recipe.repipe_project.repositories.RecipeRepository;
import com.course.recipe.repipe_project.services.RecipeService;

@Controller
public class IndexController {

    private final RecipeService recipeService;


    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

   
    
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {

       model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
