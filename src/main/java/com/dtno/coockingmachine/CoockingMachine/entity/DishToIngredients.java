package com.dtno.coockingmachine.CoockingMachine.entity;

import javax.persistence.*;

@Entity
@Table(name = "dishToIngredient")
public class DishToIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private int linkID;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dishes dish;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredients ingredient;


}
