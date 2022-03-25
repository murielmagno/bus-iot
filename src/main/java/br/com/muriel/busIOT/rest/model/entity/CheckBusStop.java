package br.com.muriel.busIOT.rest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "validaOnibusParada")
public class CheckBusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long bus_id;

    @Column
    private Long busStop_id;

    @Column(nullable = false)
    private LocalDateTime timeCheck;
}
