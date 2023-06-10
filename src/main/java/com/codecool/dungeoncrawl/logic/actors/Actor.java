package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell; //deklaruje prywatne pole "cell" typu "Cell". Reprezentuje ono komórkę, w której znajduje się aktor.
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
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
