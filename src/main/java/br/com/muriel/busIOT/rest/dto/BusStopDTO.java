package br.com.muriel.busIOT.rest.dto;

import br.com.muriel.busIOT.rest.enums.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BusStopDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Direction direction;
}
