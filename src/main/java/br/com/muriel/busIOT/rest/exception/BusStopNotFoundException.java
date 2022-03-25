package br.com.muriel.busIOT.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusStopNotFoundException extends Exception{

    public BusStopNotFoundException(Long id) {
        super(String.format("Parada com id %s não foi encontrado.", id));
    }
}
