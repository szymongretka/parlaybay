package com.example.pizzamanagmentsystem.dto;

import com.example.pizzamanagmentsystem.model.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class PizzaDTO extends RepresentationModel<PizzaDTO> {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private Float price;
    private List<Ingredient> ingredients;
}
