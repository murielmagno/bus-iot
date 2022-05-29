package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.BusDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.Bus;
import br.com.muriel.busIOT.rest.model.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;


    public Bus save(BusDTO dto) throws BusAlreadyRegisteredException{
        verifyIfExistsBusName(dto.getName());
        verifyIfExistsBusNumber(dto.getBusNumber());
        Bus bus = new Bus();
        bus.setName(dto.getName());
        bus.setRoute(dto.getRoute());
        bus.setBusNumber(dto.getBusNumber());
        return busRepository.save(bus);
    }

    public Bus getById(Long id) throws BusNotFoundException {
        return verifyIfExists(id);
    }

    public List<Bus> findAll(){
        return busRepository.findAll();
    }

    private Bus verifyIfExists(Long id) throws BusNotFoundException {
        return busRepository.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));
    }

    private void verifyIfExistsBusNumber (Integer busNumber) throws BusAlreadyRegisteredException {
        Bus savedBus = busRepository.findByBusNumber(busNumber);
        if (savedBus != null) {
            throw new BusAlreadyRegisteredException(busNumber);
        }
    }

    private void verifyIfExistsBusName(String name) throws BusAlreadyRegisteredException {
        Bus savedBus = busRepository.findByName(name);
        if (savedBus != null) {
            throw new BusAlreadyRegisteredException(name);
        }
    }

    public List<Bus> findAllBus(){
        return busRepository.findAll();
    }

    public void deleteById(Long id){
        try {
            busRepository.deleteById(id);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
