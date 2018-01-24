package com.dtno.coockingmachine.CoockingMachine.entity;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_dishes")
public class UserDishes {

    public UserDishes(){
    }

    //TODO: add ingredient set (????)
    public UserDishes(String imgSrc, String name, String details, String cookingReceptie, User user ) {
        this.imgSrc = imgSrc;
        this.name = name;
        this.details = details;
        this.cookingReceptie = cookingReceptie;
        this.user = user;
    }

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
