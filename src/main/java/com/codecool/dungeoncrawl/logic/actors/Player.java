package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.Position;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Inventory> inventoryList;

    public ArrayList<Inventory> getInventoryList() {
        return inventoryList;
    }
    private Position position;

    public Player(Cell cell) {
        super(cell);
        this.position = cell.getPosition();
        inventoryList = new ArrayList<>();
    }

    public String getTileName() {
        return "player";
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

//    public void chceckPosition(int dx, int dy) {
//
//        Position playerPosition = getPosition();     // Pobierz aktualną pozycję gracza
//        Position swordPosition = Sword.getPosition(); // Pobierz aktualną pozycję miecza
//
//        if (playerPosition.equals(swordPosition)) {
//            // Gracz i miecz znajdują się na tym samym miejscu
//            // Dodaj odpowiedni kod lub logikę w tej sekcji
//        } else {
//            // Nie ma żadnych zmian do wykonania
//            // Nie musisz dodawać żadnego kodu w tej sekcji
//        }
//    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);

        if(nextCell.getActor() instanceof Inventory){
            inventoryList.add((Inventory) nextCell.getActor());
        }

        super.move(dx,dy);
    }
}
