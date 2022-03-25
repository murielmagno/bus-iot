package br.com.muriel.busIOT.rest.model.entity;

import br.com.muriel.busIOT.rest.enums.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.Mapping;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    private BigDecimal latitude;

    @Column(precision=17, scale=14)
    private BigDecimal longitude;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Direction direction;

    @JsonIgnore
    @ManyToMany(mappedBy ="busStop",cascade = CascadeType.ALL)
    private List<Bus> bus;
}
