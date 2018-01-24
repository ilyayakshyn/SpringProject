package com.dtno.coockingmachine.CoockingMachine.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient_categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int catgoryId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Ingredients> ingredientsSet = new HashSet<>();
}
