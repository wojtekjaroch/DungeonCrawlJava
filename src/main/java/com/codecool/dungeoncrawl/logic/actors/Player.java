package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Position;

public class Player extends Actor {
    private Position position;

    public Player(Cell cell) {
        super(cell);
        this.position = cell.getPosition();
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

    public void chceckPosition(int dx, int dy) {
        // Kod odpowiedzialny za przesunięcie gracza

        // Pobierz aktualną pozycję gracza
        Position playerPosition = getPosition();
        System.out.println(playerPosition);


        // Pobierz pozycję miecza (zakładając, że sword to obiekt klasy Sword)
        Position swordPosition = Sword.getPosition();

        if (playerPosition.equals(swordPosition)) {
            // Gracz i miecz znajdują się na tym samym miejscu
            // Dodaj odpowiedni kod lub logikę w tej sekcji
        } else {
            // Nie ma żadnych zmian do wykonania
            // Nie musisz dodawać żadnego kodu w tej sekcji
        }
    }
}
