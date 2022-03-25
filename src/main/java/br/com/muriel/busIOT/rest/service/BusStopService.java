package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.BusStopDTO;
import br.com.muriel.busIOT.rest.exception.BusStopAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.BusStopNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.BusStop;
import br.com.muriel.busIOT.rest.model.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    public BusStop save(BusStopDTO dto) throws BusStopAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(dto.getName());
        BusStop busStop = new BusStop();
        busStop.setName(dto.getName());
        busStop.setDirection(dto.getDirection());
        busStop.setLatitude(dto.getLatitude());
        busStop.setLongitude(dto.getLongitude());
        busStop.setDirection(dto.getDirection());
        return busStopRepository.save(busStop);
    }

    public BusStop getById(Long id) throws BusStopNotFoundException {
        return verifyIfExists(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BusStopAlreadyRegisteredException {
        BusStop savedBusStop = busStopRepository.findByName(name);
        if (savedBusStop != null) {
            throw new BusStopAlreadyRegisteredException(name);
        }
    }

    private BusStop verifyIfExists(Long id) throws BusStopNotFoundException {
        return busStopRepository.findById(id)
                .orElseThrow(() -> new BusStopNotFoundException(id));
    }
}
