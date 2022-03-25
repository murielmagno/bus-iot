package br.com.muriel.busIOT.rest.controller;

import br.com.muriel.busIOT.rest.dto.CheckBusStopDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import br.com.muriel.busIOT.rest.service.CheckBusStopService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/check")
public class CheckBusStopController {

    @Autowired
    private CheckBusStopService checkBusStopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Enviar posição do buzão")
    public ResponseEntity<CheckBusStop> save(@RequestBody CheckBusStopDTO checkBusStopDTO) throws BusStopNotFoundException, BusNotFoundException {
        return ResponseEntity.status(201).body(checkBusStopService.save(checkBusStopDTO));
    }
}
