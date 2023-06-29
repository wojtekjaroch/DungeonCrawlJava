package com.codecool.dungeoncrawl.logic;

import lombok.Data;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Date;

@Data
public class Saves {

    private GameMap gameMap;
    private SaveDAO saveDAO;

    public Saves(GameMap gameMap, SaveDAO saveDAO) {
        this.gameMap = gameMap;
        this.saveDAO = saveDAO;
    }

    public void init() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        try {
            Save save = new Save();
            save.setSaveTime(new Timestamp(new Date().getTime()));
            save.setSavesStatus(readMapFile());

            saveDAO.save(save);

            System.out.println("Game saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String readMapFile() {
        try {
            return Files.readString(Path.of("resources/map.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}


