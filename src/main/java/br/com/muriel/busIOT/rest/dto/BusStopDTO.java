package br.com.muriel.busIOT.rest.dto;

import br.com.muriel.busIOT.rest.enums.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BusStopDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Direction direction;
}
