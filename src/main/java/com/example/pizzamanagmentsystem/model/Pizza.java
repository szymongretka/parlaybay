package com.example.pizzamanagmentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Float price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pizza_ingredient",
            joinColumns = { @JoinColumn(name = "pizza_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    private List<Ingredient> ingredients = new ArrayList<>();

}
