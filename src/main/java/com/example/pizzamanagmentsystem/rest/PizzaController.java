package com.example.pizzamanagmentsystem.rest;

import com.example.pizzamanagmentsystem.dto.PizzaDTO;
import com.example.pizzamanagmentsystem.model.Pizza;
import com.example.pizzamanagmentsystem.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/pizza/")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping(value = "all")
    public CollectionModel<PizzaDTO> findPizzas(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {

        Page<PizzaDTO> pizzasDTOs = pizzaService.findPizzas(page, size);

        for (final PizzaDTO pizzaDTO : pizzasDTOs.getContent()) {
            Link selfLink = linkTo(methodOn(PizzaController.class)
                    .findPizzaByName(String.valueOf(pizzaDTO.getName()))).withSelfRel();
            pizzaDTO.add(selfLink);
        }

        Link link = linkTo(methodOn(PizzaController.class).findPizzas(page, size)).withSelfRel();

        return CollectionModel.of(pizzasDTOs.getContent(), link);
    }

    @PostMapping(path = "create")
    public Pizza createPizza(@RequestBody PizzaDTO pizzaDTO) {
        return pizzaService.createPizza(pizzaDTO);
    }

    @PutMapping("{id}")
    public String updatePizza(@RequestBody PizzaDTO pizzaDTO, @PathVariable String id) {
        pizzaService.updatePizza(pizzaDTO, id);
        return "Updated pizza with id: " + id;
    }

    @DeleteMapping("delete/{id}")
    public String deletePizza(@PathVariable String id) {
        pizzaService.deletePizza(id);
        return "Deleted pizza with id: " + id;
    }

    @GetMapping("name/{name}")
    public PizzaDTO findPizzaByName(@PathVariable String name) {
        return pizzaService.findPizzaByName(name);
    }

    @GetMapping("{id}")
    public PizzaDTO findPizza(@PathVariable String id) {
        return pizzaService.findPizzaById(id);
    }

}
