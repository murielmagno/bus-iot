package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.CheckBusStopDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import br.com.muriel.busIOT.rest.model.repository.CheckBusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CheckBusStopService {

    @Autowired
    private CheckBusStopRepository checkBusStopRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private BusStopService busStopService;


    public CheckBusStop save(CheckBusStopDTO checkBusStopDTO) throws BusNotFoundException, BusStopNotFoundException {
        busService.getById(checkBusStopDTO.getBus_id());
        busStopService.getById(checkBusStopDTO.getBusStop_id());
        CheckBusStop checkBusStop = new CheckBusStop();
        checkBusStop.setBusStop_id(checkBusStopDTO.getBusStop_id());
        checkBusStop.setBus_id(checkBusStopDTO.getBus_id());
        checkBusStop.setTimeCheck(LocalDateTime.now(Clock.system(ZoneId.of("America/Fortaleza"))));
        return checkBusStopRepository.save(checkBusStop);
    }

    public List<CheckBusStop> nextBusInCheck(Long id){

        return checkBusStopRepository.findBusByStop(id);


    }

}
