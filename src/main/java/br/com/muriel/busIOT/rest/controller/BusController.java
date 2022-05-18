package br.com.muriel.busIOT.rest.controller;

import br.com.muriel.busIOT.rest.dto.BusDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.Bus;
import br.com.muriel.busIOT.rest.service.BusService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um buzão!")
    public ResponseEntity<Bus> save(@RequestBody BusDTO dto) throws BusAlreadyRegisteredException {
        return ResponseEntity.status(201).body(busService.save(dto));
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar onibus pelo id")
    public ResponseEntity<Bus> getById(@PathVariable("id") Long id) throws BusNotFoundException {
        return ResponseEntity.status(200).body((busService.getById(id)));
    }

    @DeleteMapping("{id")
    @Operation(summary = "Delete onibus")
    public void delete(@PathVariable("id") Long id){
        busService.deleteById(id);
    }

}
