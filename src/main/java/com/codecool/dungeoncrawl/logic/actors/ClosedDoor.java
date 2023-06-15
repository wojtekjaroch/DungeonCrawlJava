package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClosedDoor extends Actor {
    public ClosedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ClosedDoor";
    }

    public static Position getPosition() {
        return new Position(getCell().getX(), getCell().getY());
    }
}