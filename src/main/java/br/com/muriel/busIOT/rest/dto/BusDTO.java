package br.com.muriel.busIOT.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BusDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private int busNumber;
    private String route;
}
