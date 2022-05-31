package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.BusStopDTO;
import br.com.muriel.busIOT.rest.exception.bus.BusAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.bus.BusNotFoundException;
import br.com.muriel.busIOT.rest.exception.busStop.NotFoundBusStopNext;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopAlreadyRegisteredException;
import br.com.muriel.busIOT.rest.exception.busStop.BusStopNotFoundException;
import br.com.muriel.busIOT.rest.model.entity.Bus;
import br.com.muriel.busIOT.rest.model.entity.BusStop;
import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import br.com.muriel.busIOT.rest.model.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;
    @Autowired
    private CheckBusStopService checkBusStopService;
    @Autowired
    private BusService busService;

    public BusStop save(BusStopDTO dto) throws BusStopAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(dto.getName());
        verifyIfExistsBusStopLatLong(dto.getLatitude(), dto.getLongitude());
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

    public Map<String, Double> nextBus(Long id) throws BusStopNotFoundException, NotFoundBusStopNext, BusNotFoundException {
        List<CheckBusStop> listBusCheck = checkBusStopService.nextBusInCheck();
        Map<String, Double> mapCheckBus = new HashMap<>();
        Bus bus = new Bus();
        if (listBusCheck != null && !listBusCheck.isEmpty()) {
            for (CheckBusStop obj : listBusCheck) {
                Map<Long, Double> busStops = backDistance(id);
                bus = busService.getById(obj.getId());
                mapCheckBus.put(bus.getName(), busStops.get(obj.getBusStop_id()));
            }
            if (mapCheckBus.containsKey(id) && mapCheckBus.containsValue(null)) {
                mapCheckBus.remove(id);
            }
            return mapCheckBus;
        }
        return mapCheckBus;
    }

    private Map<Long, Double> backDistance(Long id) throws BusStopNotFoundException {
        BusStop busStop = busStopRepository.findById(id).orElseThrow(() -> new BusStopNotFoundException(id));
        List<BusStop> listBusStop = busStopRepository.findAll();
        Map<Long, Double> map = new HashMap<>();
        double distancia;
        double segundos;
        double minutos;
        if (listBusStop != null && !listBusStop.isEmpty()) {
            for (BusStop obj : listBusStop) {
                if (obj.getId() < busStop.getId() &&
                        obj.getId() != busStop.getId() &&
                        busStop.getDirection() == obj.getDirection()) {
                    distancia = Math.sqrt(
                            (Math.pow(obj.getLatitude() - busStop.getLatitude(), 2)) +
                                    (Math.pow(obj.getLongitude() - busStop.getLongitude(), 2)));
                    segundos = (((distancia * 1000 )/ 60));
                    minutos = (segundos / 60) * 100;
                    map.put(obj.getId(), minutos);
                } else {
                    continue;
                }
            }
            return map;
        }
        return map;
    }

    public List<BusStop> findAllBusStop() {
        return busStopRepository.findAll();
    }

    private void verifyIfExistsBusStopLatLong(double latitude, double longitude) throws BusStopAlreadyRegisteredException {
        BusStop savedBusStop = busStopRepository.findByLatitudeAndLongitude(latitude, longitude);
        if (savedBusStop != null) {
            throw new BusStopAlreadyRegisteredException(latitude, longitude);
        }
    }

    public void deleteById(Long id) {
        try {
            busStopRepository.deleteById(id);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
