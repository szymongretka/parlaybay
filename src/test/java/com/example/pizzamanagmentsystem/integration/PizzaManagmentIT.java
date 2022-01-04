package com.example.pizzamanagmentsystem.integration;

import com.example.pizzamanagmentsystem.dto.PizzaDTO;
import com.example.pizzamanagmentsystem.handler.PizzaExceptionHandler;
import com.example.pizzamanagmentsystem.model.Pizza;
import com.example.pizzamanagmentsystem.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PizzaManagmentIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PizzaRepository pizzaRepository;

    @MockBean
    private PizzaExceptionHandler exceptionHandler;

    private String applicationApiUrl = "/pizza/";

    @Test
    void testGetApplicationById_expectApplication() {
        //given
        String name = "name";
        Pizza givenPizza = new Pizza();
        givenPizza.setName(name);
        givenPizza.setPrice(2.50f);
        pizzaRepository.saveAndFlush(givenPizza);

        //when
        PizzaDTO pizzaDTO = restTemplate
                .getForObject("http://localhost:" + port + applicationApiUrl + 1, PizzaDTO.class);

        //then
        assertEquals(name, givenPizza.getName());
    }

    @Test
    void testGetApplicationById_withInvalidId_expectExceptionThrownAndHandled() {
        //given
        String url = "http://localhost:" + port + applicationApiUrl + 100;

        //when
        restTemplate.getForObject(url, PizzaDTO.class);

        //then
        verify(exceptionHandler, times(1)).pizzaNotFoundException(any(), any());
    }


}
