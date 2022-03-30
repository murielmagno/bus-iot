package br.com.muriel.busIOT.rest.service;

import br.com.muriel.busIOT.rest.dto.BusStopDTO;
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

    public Map<Long, Double> nextBus(Long id) throws BusStopNotFoundException, NotFoundBusStopNext {
        List<CheckBusStop> listBusCheck = checkBusStopService.nextBusInCheck();
        Map<Long, Double> mapCheckBus = new HashMap<>();
        if(listBusCheck != null && !listBusCheck.isEmpty()){
            for (CheckBusStop obj : listBusCheck) {
                    Map<Long, Double> busStops = backDistance(id);
                    mapCheckBus.put(obj.getBus_id(), busStops.get(obj.getBusStop_id()));
            }return mapCheckBus;
        }return mapCheckBus;
    }

    private Map<Long, Double> backDistance(Long id) throws BusStopNotFoundException {
        BusStop busStop = busStopRepository.findById(id).orElseThrow(() -> new BusStopNotFoundException(id));
        List<BusStop> listBusStop = busStopRepository.findAll();
        Map<Long,Double> map = new HashMap<>();
        double distancia;
        if(listBusStop != null && !listBusStop.isEmpty()){
            for (BusStop obj : listBusStop) {
                if (obj.getId() < busStop.getId() &&
                        obj.getId()!=busStop.getId() &&
                        busStop.getDirection() == obj.getDirection()){
                    distancia = Math.sqrt(
                            (Math.pow(obj.getLatitude() - busStop.getLatitude(), 2)) + (Math.pow(obj.getLongitude() - busStop.getLongitude(), 2)));
                    map.put(obj.getId(),(distancia*100));
                }else{
                    continue;
                }
            }return map;
        }return map;
    }

    private List<Bus> findAllBus(){
        return busService.findAll();
    }

    private List<BusStop> findAllBusStop(){
        return busStopRepository.findAll();
    }
    
    private void nextDistance(){

    }
}
