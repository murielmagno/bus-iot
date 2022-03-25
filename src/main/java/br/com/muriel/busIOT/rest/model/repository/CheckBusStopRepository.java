package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckBusStopRepository extends JpaRepository<CheckBusStop,Long> {
}
