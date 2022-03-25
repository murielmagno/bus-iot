package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.model.repository.CheckBusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckBusStopService {

    @Autowired
    private CheckBusStopRepository checkBusStopRepository;
}
