package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;

public class Key extends Actor implements Inventory {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}