package br.com.muriel.busIOT.rest.model.entity;

import br.com.muriel.busIOT.rest.enums.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "busStop")
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "busStop_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(precision=17, scale=14)
    private double latitude;

    @Column(precision=17, scale=14)
    private double longitude;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Direction direction;

    @JsonIgnore
    @ManyToMany(mappedBy ="busStop",cascade = CascadeType.ALL)
    private List<Bus> bus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusStop busStop = (BusStop) o;
        return Double.compare(busStop.latitude, latitude) == 0 && Double.compare(busStop.longitude, longitude) == 0 && Objects.equals(id, busStop.id) && Objects.equals(name, busStop.name) && direction == busStop.direction && Objects.equals(bus, busStop.bus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, direction, bus);
    }
}
