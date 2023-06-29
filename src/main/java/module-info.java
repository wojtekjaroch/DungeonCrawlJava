module com.codecool.dungeoncrawl {

        requires javafx.controls;
//        requires javafx.fxml;
        requires static lombok;
//        requires javafx.media;
        requires java.datatransfer;
        requires java.desktop;
        requires java.sql;


        opens com.codecool.dungeoncrawl to javafx.fxml;
        exports com.codecool.dungeoncrawl;
        }