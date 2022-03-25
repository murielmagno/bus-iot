package br.com.muriel.busIOT.rest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long id;

    @Column
    private String name;

    @Column
    private int busNumber;

    @Column
    private String route;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="bus_busStops",
            joinColumns={@JoinColumn(name="bus_id")},
            inverseJoinColumns={@JoinColumn(name="busStop_id")})
    private List<BusStop> busStop;
}
