package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop,Long> {

    BusStop findByLatitudeAndLongitude(String lat, String lon);

    BusStop findByName(String name);
}
