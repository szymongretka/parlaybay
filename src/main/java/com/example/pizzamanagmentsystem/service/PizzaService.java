package com.example.pizzamanagmentsystem.service;

import com.example.pizzamanagmentsystem.dto.PizzaDTO;
import com.example.pizzamanagmentsystem.model.Pizza;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PizzaService {
    Pizza createPizza(PizzaDTO pizzaDTO);
    void updatePizza(PizzaDTO pizzaDTO, String id);
    PizzaDTO findPizzaById(String id);
    PizzaDTO findPizzaByName(String name);
    Page<PizzaDTO> findPizzas(int page, int size);
    void deletePizza(String id);
}
