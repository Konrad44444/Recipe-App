package com.course.recipe.repipe_project.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.course.recipe.repipe_project.domain.Recipe;
import com.course.recipe.repipe_project.repositories.RecipeRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl  implements ImageService{

    private final RecipeRepository recipeRepository;


    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {
        log.debug("Received a file");
        
        try {
            Recipe recipe = recipeRepository.findById(id).get();
            
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()) byteObjects[i++] = b;
            
            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occured", e);
            e.printStackTrace();
        }
        
    }
    
}
