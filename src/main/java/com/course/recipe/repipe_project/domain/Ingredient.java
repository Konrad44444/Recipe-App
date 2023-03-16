package com.course.recipe.repipe_project.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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

    public Ingredient(String desc, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = desc;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public UnitOfMeasure getUom() {
        return this.uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

}
