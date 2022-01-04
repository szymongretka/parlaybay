package com.example.pizzamanagmentsystem.dto;

import com.example.pizzamanagmentsystem.model.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
public class PizzaDTO extends RepresentationModel<PizzaDTO> {
    private String name;
    private Float price;
    private List<Ingredient> ingredients;
}
