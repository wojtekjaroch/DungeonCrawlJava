package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.*;

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
        else if(nextCell.getActor() instanceof Monster){
            fight(nextCell);
        }


        super.move(dx,dy);
    }

    private void fight(Cell nextCell) {
        int attack = 5;

        if(inventoryList.stream().anyMatch(a->a instanceof Sword)){
            attack += 2;
        }

        nextCell.getActor().setHealth(nextCell.getActor().getHealth() - attack);

        if(nextCell.getActor().getHealth() > 0){
            this.setHealth(this.getHealth() - 2);
        }
    }


    public String getSword() {
        return GameMap.getPlayer().getSword();
    }
}
