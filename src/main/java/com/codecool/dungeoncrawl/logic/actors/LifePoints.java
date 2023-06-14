package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class LifePoints extends Actor {
    public LifePoints(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "life-points";
    }
}