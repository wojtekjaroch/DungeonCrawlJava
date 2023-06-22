package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

import javafx.util.Duration;
import java.io.File;


public class Main extends Application {
    private MediaPlayer mediaPlayer;
    private ClassLoader classLoader = getClass().getClassLoader();

    private GameMap map = MapLoader.loadMap();
    private Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private GraphicsContext context = canvas.getGraphicsContext2D();
    private Label healthLabel = new Label();
    private Label swordLabel = new Label();//WJ
    private Label shieldLabel = new Label();//WJ
    private Label keyLabel = new Label();//WJ
    ListView inventoryList = new ListView();//WJ
    /*ListView inventoryList = new ListView(); - Ta linia kodu tworzy nowy obiekt ListView o nazwie inventoryList.
    ListView jest komponentem interfejsu użytkownika w bibliotece JavaFX, który wyświetla listę elementów w pionowej
    kolejności. Oznacza to, że zmienna inventoryList jest obiektem typu ListView, który zostaje utworzony i
    przygotowany do wyświetlania listy. Po wykonaniu tej linii kodu, mamy nowy obiekt ListView o nazwie inventoryList,
    który możemy dostosować i używać do wyświetlania elementów listy w naszej aplikacji.*/

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//         Ładujemy plik dźwiękowy
//        String soundFile = "src/main/resources/261491_kradziej_ambinet-hell.mp3";
//        String soundPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(soundFile)).toExternalForm();
//        System.out.println((getClass().getResource("/261491_kradziej_ambinet-hell.mp3").toString()));
//        Media media = new Media(getClass().getResource("/261491_kradziej_ambinet-hell.mp3").toURI().getPath());

//        String soundFile = "/261491_kradziej_ambinet-hell.mp3";
//        URL soundURL = getClass().getResource(soundFile);
//        if (soundURL != null) {
//            Media media = new Media(soundURL.toExternalForm());
//
//            // Tworzymy odtwarzacz dźwięku i ustawiamy zapętlenie
//            mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//
//            // Odtwarzamy dźwięk
//            mediaPlayer.play();
//        } else {
//            System.out.println("Nie można znaleźć pliku dźwiękowego: " + soundFile);
//        }

//        GridPane ui = new GridPane();
//        ui.setPrefWidth(200);
//        ui.setPadding(new Insets(10));
//
//        ui.add(new Label("Health: "), 0, 0);
//        ui.add(healthLabel, 1, 0);

        inventoryList.setFocusTraversable(false);
        inventoryList.setMaxHeight(150);

        VBox ui = new VBox(new Label("Health: "),healthLabel,new Label("Inventory: "),inventoryList);
        // Po wykonaniu tej linii kodu, mamy nowy obiekt VBox o nazwie ui,
        // który zawiera elementy Label i ListView, uporządkowane w pionowej kolejności.
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

//        ui.add(new Label("Sword: "), 0, 1);//WJ
//        ui.add(swordLabel, 1, 1);//WJ
//
//        ui.add(new Label("Shield: "), 0, 2);//WJ
//        ui.add(shieldLabel, 1, 2);//WJ
//
//        ui.add(new Label("Key: "), 0, 3);//WJ
//        ui.add(keyLabel, 1, 3);//WJ

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setScene(scene);
        refresh();

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        // pickup button
        Button pickUpButton = new Button("Pick up the item!");
        pickUpButton.setFocusTraversable(false);

        pickUpButton.setOnMouseClicked(mouseEvent -> {
            inventoryList.getItems().clear(); // Usuwa wszystkie elementy z listy

            for (Inventory inv : map.getPlayer().getInventoryList()) {
                inventoryList.getItems().add(inv.toString());
            }
        });

        Pane rightMenuPane = new Pane();
        rightMenuPane.getChildren().addAll(pickUpButton);
        rightMenuPane.setLayoutX(810);
        rightMenuPane.setLayoutY(605);

        Pane mainPanel = new Pane();
        mainPanel.getChildren().addAll(canvas, rightMenuPane);

        borderPane.setCenter(mainPanel);
        canvas.requestFocus();
    }

    public void pickUpItem() {
        System.out.println("WYDRUK!!!");
    }
//
    private void onKeyPressed(KeyEvent keyEvent) {
        int dx = 0;
        int dy = 0;

        switch (keyEvent.getCode()) {
            case UP:
                dx = 0;
                dy = -1;
                break;
            case DOWN:
                dx = 0;
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                dy = 0;
                break;
            case RIGHT:
                dx = 1;
                dy = 0;
                break;
        }

        Cell nextCell = map.getPlayer().getCell().getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR) {
            map.getPlayer().move(dx, dy);
        } else {
            map.getPlayer().move(0, 0);
        }
        refresh();
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());

//        inventoryList.getItems().clear(); // usuwa wszystkie elementy z listy
//        pickUpButton.setOnMouseClicked(mouseEvent -> {
//        for(Inventory inv : map.getPlayer().getInventoryList())
//        //odnosi się do obiektu gracza na mapie przez map.getPlayer()) i wywołuje metodę
//            // getInventoryList(), która zwraca listę elementów Inventory.
//        {
//            inventoryList.getItems().add(inv.toString());
//        }
    }
}
