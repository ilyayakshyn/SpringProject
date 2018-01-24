package com.dtno.coockingmachine.CoockingMachine.entity;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private int dishId;

    @Column(name = "img_src")
    private String imgSrc;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "details", length = 300)
    private String details;


    @Column(name = "cooking_receptie", length = 25000)
    private String cookingReceptie;

    @OneToMany(mappedBy = "dish")
    private Set<DishToIngredients> dishToIngredientsSet = new HashSet<>();

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCookingReceptie() {
        return cookingReceptie;
    }

    public void setCookingReceptie(String cookingReceptie) {
        this.cookingReceptie = cookingReceptie;
    }
}
