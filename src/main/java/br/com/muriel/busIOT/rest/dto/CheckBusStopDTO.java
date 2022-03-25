package br.com.muriel.busIOT.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckBusStopDTO {

    @JsonIgnore
    private Long id;
    private Long bus_id;
    private Long busStop_id;
    @JsonIgnore
    private LocalDateTime timeCheck;
}
