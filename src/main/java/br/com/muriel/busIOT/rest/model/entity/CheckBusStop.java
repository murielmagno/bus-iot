package br.com.muriel.busIOT.rest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "checkBusStop")
public class CheckBusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bus_id;

    @Column
    private Long busStop_id;

    @Column
    private LocalDateTime timeCheck;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckBusStop that = (CheckBusStop) o;
        return Objects.equals(id, that.id) && Objects.equals(bus_id, that.bus_id) && Objects.equals(busStop_id, that.busStop_id) && Objects.equals(timeCheck, that.timeCheck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bus_id, busStop_id, timeCheck);
    }
}
