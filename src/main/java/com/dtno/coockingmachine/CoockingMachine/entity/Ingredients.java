package com.dtno.coockingmachine.CoockingMachine.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int ingredientId;

    @Column(name = "name", length = 75)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_of_ingredient")
    private Categories category;

    @OneToMany(mappedBy = "ingredient")
    private Set<DishToIngredients> dishToIngredientsSet = new HashSet<>();

    public int getIngredient_id() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
