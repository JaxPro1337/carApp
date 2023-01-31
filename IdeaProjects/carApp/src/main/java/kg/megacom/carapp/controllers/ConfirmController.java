package kg.megacom.carapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kg.megacom.carapp.db.DbHelper;
import kg.megacom.carapp.services.CarService;

public class ConfirmController {
    //private CarService carService = CarService.getINSTANCE();
    private DbHelper dbHelper = DbHelper.getInstance();

    @FXML
    private Label lblConfirm;

    @FXML
    void onCancel(ActionEvent event) {
        lblConfirm.getScene().getWindow().hide();
    }

    @FXML
    void onYes(ActionEvent event) {
        DbHelper dbHelper = DbHelper.getInstance();
        dbHelper.deleteCar();
        //carService.deleteCar();
        onCancel(null);
    }

}
