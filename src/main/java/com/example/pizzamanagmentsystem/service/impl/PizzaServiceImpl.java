package com.example.pizzamanagmentsystem.service.impl;

import com.example.pizzamanagmentsystem.dto.PizzaDTO;
import com.example.pizzamanagmentsystem.exception.PizzaNotFoundException;
import com.example.pizzamanagmentsystem.model.Ingredient;
import com.example.pizzamanagmentsystem.model.Pizza;
import com.example.pizzamanagmentsystem.repository.PizzaRepository;
import com.example.pizzamanagmentsystem.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Pizza createPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);
        return pizzaRepository.save(pizza);
    }

    @Transactional
    public void updatePizza(PizzaDTO pizzaDTO, String id) {
        Pizza pizza = pizzaRepository.getById(convertedId(id));

        pizza.setName(pizzaDTO.getName());
        pizza.setPrice(pizzaDTO.getPrice());
        pizza.setIngredients(pizzaDTO.getIngredients());

        pizzaRepository.save(pizza);
    }

    @Transactional(readOnly = true)
    public PizzaDTO findPizzaById(String id) {
        Pizza pizza = pizzaRepository.findById(convertedId(id)).orElseThrow(PizzaNotFoundException::new);
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<PizzaDTO> findPizzas(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Pizza> pizzasPage = pizzaRepository.findAll(pageable);
        int totalElements = (int) pizzasPage.getTotalElements();
        List<PizzaDTO> pizzaDTOS = modelMapper.map(pizzasPage.getContent(), new TypeToken<List<PizzaDTO>>(){}.getType());

        return new PageImpl<>(pizzaDTOS, pageable, totalElements);
    }

    @Transactional
    public void deletePizza(String id) {
        pizzaRepository.deleteById(convertedId(id));
    }

    @Override
    public PizzaDTO findPizzaByName(String name) {
        Pizza pizza = pizzaRepository.findPizzaByName(name).orElseThrow(PizzaNotFoundException::new);
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    private Integer convertedId(String paramId) {
        return Integer.valueOf(paramId);
    }
}
