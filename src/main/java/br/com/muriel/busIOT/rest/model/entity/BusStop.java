package br.com.muriel.busIOT.rest.model.entity;

import br.com.muriel.busIOT.rest.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "busStop")
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Direction direction;
}
