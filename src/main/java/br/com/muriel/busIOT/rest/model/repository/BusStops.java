package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusStops extends JpaRepository<BusStop,Integer> {

    BusStop findByLatLong(double latitude, double longitude);

    Optional<BusStop> findById(Integer id);
}
