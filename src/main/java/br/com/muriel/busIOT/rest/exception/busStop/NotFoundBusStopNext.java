package br.com.muriel.busIOT.rest.exception.busStop;

public class NotFoundBusStopNext extends Exception {
    public NotFoundBusStopNext(Long id) {
        super(String.format("Não encontramos paradas proximas a parada %s .", id));
    }
}
