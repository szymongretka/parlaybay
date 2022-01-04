package com.example.pizzamanagmentsystem.repository;

import com.example.pizzamanagmentsystem.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Override
    @EntityGraph(attributePaths = "ingredients")
    Page<Pizza> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "ingredients")
    Optional<Pizza> findPizzaByName(String name);

}
