package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;

public class LifePoints extends Actor implements Inventory {
    public LifePoints(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "life-points";
    }

    @Override
    public String toString() {
        return "Life-points";
    }
}