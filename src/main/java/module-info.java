module com.codecool.dungeoncrawl {

        requires javafx.controls;
        requires javafx.fxml;
        requires static lombok;


        opens com.codecool.dungeoncrawl to javafx.fxml;
        exports com.codecool.dungeoncrawl;
        }