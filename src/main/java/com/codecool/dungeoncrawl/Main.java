package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    Label swordLabel = new Label();//WJ
    Label shieldLabel = new Label();//WJ
    Label keyLabel = new Label();//WJ


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

//        ui.add(new Label("Health: "), 0, 0);
//        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
//        ui.add(new Label("Health: "), 0, 2);
//        ui.add(new Label("Health: "), 0, 3);
//        ui.add(new Label("Health: "), 0, 4);
//        ui.add(new Label("Health: "), 0, 5);
//
//        ui.add(new Label("Health: "), 0, 6);
//        ui.add(new Label("Health: "), 0, 7);
//        ui.add(new Label("Health: "), 0, 8);
//        ui.add(new Label("Health: "), 0, 9);
//        ui.add(new Label("Health: "), 0, 10);

        ui.add(new Label("Sword: "), 0, 1);//WJ
        ui.add(swordLabel, 1, 1);//WJ

        ui.add(new Label("Shield: "), 0, 2);//WJ
        ui.add(shieldLabel, 1, 2);//WJ

        ui.add(new Label("Key: "), 0, 3);//WJ
        ui.add(keyLabel, 1, 3);//WJ

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
        pickUpButton.setFocusTraversable(false); // Dodajemy tę linię, aby po uzyciu przycisku, focus wrócił na scenę

        pickUpButton.setOnMouseClicked(mouseEvent -> {
            pickUpItem();
        });

        Pane rightMenuPane = new Pane();
        rightMenuPane.getChildren().addAll(pickUpButton);
        rightMenuPane.setLayoutX(810);
        rightMenuPane.setLayoutY(100);

        Pane mainPanel = new Pane();
        mainPanel.getChildren().addAll(canvas, rightMenuPane);

        borderPane.setCenter(mainPanel); // Dodajemy mainPanel do centralnej części borderPane
        canvas.requestFocus();

    }

    public void pickUpItem(){
        System.out.println("WYDRUK!!!");
    };

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
    }
}
