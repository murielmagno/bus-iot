package br.com.muriel.busIOT.rest.controller;

import br.com.muriel.busIOT.rest.dto.BusStopDTO;
import br.com.muriel.busIOT.rest.exception.BusStopAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.model.entity.BusStop;
import br.com.muriel.busIOT.rest.service.BusStopService;
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
@RequestMapping("/busstop")
public class BusStopController {

    @Autowired
    private BusStopService busStopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um parada!")
    public ResponseEntity<BusStop> save(@RequestBody BusStopDTO dto) throws BusStopAlreadyRegisteredException {
        BusStop busStop = busStopService.save(dto);
        return ResponseEntity.status(201).body(busStop);
    }
}
