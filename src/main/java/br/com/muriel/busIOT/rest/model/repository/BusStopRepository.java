package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop,Long> {

    BusStop findByLatitudeAndLongitude(double lat, double lon);

    BusStop findByName(String name);
}
