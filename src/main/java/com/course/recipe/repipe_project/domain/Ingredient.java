package com.course.recipe.repipe_project.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) //get this from db every time
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;
    
    public Ingredient() {
    }
    
    public Ingredient(String desc, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = desc;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
