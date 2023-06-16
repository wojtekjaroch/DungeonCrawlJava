package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sword extends Actor implements Inventory {
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public String toString() {
        return "Sword";
    }

    public static Position getPosition() {
        return new Position(getCell().getX(), getCell().getY());
    }
}
