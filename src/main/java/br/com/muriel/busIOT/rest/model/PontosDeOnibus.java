package br.com.muriel.busIOT.rest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Pontos")
public class PontosDeOnibus {

    @Id
    private Long id;
    private String nome;
    private String latitude;
    private String longitude;

}
