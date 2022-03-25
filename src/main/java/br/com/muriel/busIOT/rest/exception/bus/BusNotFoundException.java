package br.com.muriel.busIOT.rest.exception.bus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusNotFoundException extends Exception{

    public BusNotFoundException(Long id) {
        super(String.format("Onibus com id %s não foi encontrado.", id));
    }
}
