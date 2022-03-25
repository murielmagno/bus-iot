package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.BusDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.Bus;
import br.com.muriel.busIOT.rest.model.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;


    public Bus save(BusDTO dto) throws BusAlreadyRegisteredException{
        verifyIfIsAlreadyRegistered(dto.getName());
        Bus bus = new Bus();
        bus.setName(dto.getName());
        bus.setRoute(dto.getRoute());
        bus.setBusNumber(dto.getBusNumber());
        return busRepository.save(bus);
    }

    public Bus getById(Long id) throws BusNotFoundException {
        return verifyIfExists(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BusAlreadyRegisteredException {
        Bus savedBusStop = busRepository.findByName(name);
        if (savedBusStop != null) {
            throw new BusAlreadyRegisteredException(name);
        }
    }

    private Bus verifyIfExists(Long id) throws BusNotFoundException {
        return busRepository.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));
    }
}
