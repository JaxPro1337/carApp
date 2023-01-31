module kg.megacom.carapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kg.megacom.carapp to javafx.fxml;
    exports kg.megacom.carapp;
    exports kg.megacom.carapp.controllers;
    opens kg.megacom.carapp.controllers to javafx.fxml;
    exports kg.megacom.carapp.models;
    opens kg.megacom.carapp.models to javafx.fxml;
}