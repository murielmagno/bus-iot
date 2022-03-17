package br.com.muriel.busIOT.rest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    A("a"),
    B("b");

    private final String descricao;
}
