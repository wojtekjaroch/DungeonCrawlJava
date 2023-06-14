package com.codecool.dungeoncrawl.logic;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor // automatycznie wygeneruje konstruktor, który przyjmuje jako argumenty wszystkie pola klasy.
@Getter
@Setter
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
}
