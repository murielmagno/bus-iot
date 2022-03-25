package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.CheckBusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckBusStopRepository extends JpaRepository<CheckBusStop,Long> {

    @Query(value="select * from VALIDA_ONIBUS_PARADA as vop where bus_id = ?1", nativeQuery=true)
    CheckBusStop findByIdBus (Long id);
}
