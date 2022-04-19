package br.com.muriel.busIOT.rest.controller;

import br.com.muriel.busIOT.rest.dto.BusStopDTO;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopNotFoundException;
import br.com.muriel.busIOT.rest.exception.busStop.NotFoundBusStopNext;
import br.com.muriel.busIOT.rest.model.entity.Bus;
import br.com.muriel.busIOT.rest.model.entity.BusStop;
import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import br.com.muriel.busIOT.rest.service.BusStopService;
import br.com.muriel.busIOT.rest.service.CheckBusStopService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/busstop")
public class BusStopController {

    @Autowired
    private BusStopService busStopService;

    @Autowired
    private CheckBusStopService checkBusStopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um parada!")
    public ResponseEntity<BusStop> save(@RequestBody BusStopDTO dto) throws BusStopAlreadyRegisteredException {
        return ResponseEntity.status(201).body(busStopService.save(dto));
    }

    @GetMapping("/nextBuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar paradas proximas")
    public ResponseEntity<Map<Long,Double>> nextBuses(@PathVariable("id") Long id) throws BusStopNotFoundException, NotFoundBusStopNext {
        return ResponseEntity.status(200).body(busStopService.nextBus(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todas as paradas")
    @Cacheable
    public ResponseEntity<List<BusStop>> findAll(){
        return ResponseEntity.status(200).body(busStopService.findAllBusStop());
    }

}
