package br.com.muriel.busIOT.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusStopAlreadyRegisteredException extends Exception{

    public BusStopAlreadyRegisteredException(String name){
        super(String.format("Parada com o nome %s já está cadastrada.", name));
    }
}
