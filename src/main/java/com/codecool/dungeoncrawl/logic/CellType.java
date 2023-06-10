package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Tiles;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),

    SKELETON("skeleton"),

    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
