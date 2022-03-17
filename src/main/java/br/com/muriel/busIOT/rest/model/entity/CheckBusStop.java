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
@Table(name = "Valida_Onibus_Parada")
public class CheckBusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_bus",nullable = false)
    private Bus bus;

    @OneToOne
    @JoinColumn(name = "id_busStope",nullable = false)
    private BusStop busStop;

    @Column(nullable = false)
    private LocalDateTime timeCheck;
}
