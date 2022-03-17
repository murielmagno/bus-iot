package br.com.muriel.busIOT.rest.model.repository;

import br.com.muriel.busIOT.rest.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Buses extends JpaRepository<Bus,Integer> {

    Optional<Bus> findById(Integer id);
}
