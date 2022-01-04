package com.example.pizzamanagmentsystem.handler;

import com.example.pizzamanagmentsystem.exception.ErrorMessage;
import com.example.pizzamanagmentsystem.exception.PizzaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Date;


@RestControllerAdvice
@RequiredArgsConstructor
public class PizzaExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(PizzaNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage pizzaNotFoundException(PizzaNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                Date.from(Instant.now()),
                ex.getMessage(),
                messageSource.getMessage("error.pizza.notfound", null, LocaleContextHolder.getLocale()));
        return message;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage numberFormatException(NumberFormatException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                Date.from(Instant.now()),
                ex.getMessage(),
                messageSource.getMessage("error.incorrect.id", null, LocaleContextHolder.getLocale()));
        return message;
    }

}
