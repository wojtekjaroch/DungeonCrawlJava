package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Scorpion extends Actor implements Monster {
    public Scorpion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "scorpion";
    }
}
