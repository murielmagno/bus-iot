package br.com.muriel.busIOT.rest.exception.bus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusAlreadyRegisteredException extends Exception{

    public BusAlreadyRegisteredException(String name){
        super(String.format("Onibus com o nome %s já está cadastrada.", name));
    }
}
