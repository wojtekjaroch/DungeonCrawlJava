package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Inventory;

public abstract class Actor implements Drawable {
    private static Cell cell; //deklaruje prywatne pole "cell" typu "Cell". Reprezentuje ono komórkę, w której znajduje się aktor.
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);//Parametr this w metodzie setActor służy do przekazania bieżącej instancji aktora do metody.
    }
    /*
    Linia this.cell.setActor(this); służy do ustawienia bieżącej instancji aktora dla komórki, w której się znajduje.
    'this' odnosi się do bieżącej instancji klasy "Actor", czyli do aktora, który jest tworzony lub modyfikowany.
    'cell' odnosi się do pola instancji klasy "Actor", które reprezentuje komórkę, w której znajduje się aktor.
    'setActor(this)' jest wywołaniem metody setActor, która jest zdefiniowana w klasie "Cell". Metoda ta ustawia
    aktora dla komórki.
    Podsumowując, ta linia kodu mówi: "Ustaw bieżącą instancję aktora dla komórki, w której się znajduje".
    Dzięki temu, komórka wie, jaki aktor się w niej znajduje i może z nim współdziałać.
    */
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Cell previousCell = cell.getPreviousCell(dx, dy);
        Actor existingActor = nextCell.getActor();
        Actor previousActor = previousCell.getActor();

        if(existingActor != null && existingActor instanceof Monster){
            if(existingActor.health <= 0) {
                cell.setActor(null);
                nextCell.setType(CellType.FLOOR);
                nextCell.setActor(this);
                cell = nextCell;
            }
            else {
                cell.setActor(this);
            }
        }
        else if (existingActor != null && !(existingActor instanceof Player) && !(existingActor instanceof Inventory)) {
            // Jeśli w kolejnej komórce jest inny aktor, ale nie jest to Player
            cell.setActor(existingActor);
            nextCell.setActor(this);
            cell = nextCell;

            // Jeśli jesteśmy w komórce z innym aktorem, ustawiamy go jako poprzedniego aktora
            existingActor.setCell(cell);
        } else if (existingActor != null && existingActor instanceof Inventory) {
            // Jeśli w kolejnej komórce jest inny aktor, ale nie jest to Inventory
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

            // Jeśli jesteśmy w komórce z innym aktorem, ustawiamy go jako poprzedniego aktora
            existingActor.setCell(cell);
        }
        else {
            // Wykonujemy zmiany bez zachowywania poprzedniego aktora
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }



    private void setCell(Cell cell) {
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health){
        if(health < 0){
            this.health = 0;
        }
        else {
            this.health = health;
        }
    }

    public static Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
