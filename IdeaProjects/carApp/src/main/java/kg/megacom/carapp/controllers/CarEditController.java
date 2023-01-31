package kg.megacom.carapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import kg.megacom.carapp.db.DbHelper;
import kg.megacom.carapp.models.Car;
import kg.megacom.carapp.services.CarService;

public class CarEditController {


    @FXML
    private CheckBox checkIsNew;

    @FXML
    private Spinner<Double> spnMileage;

    @FXML
    private Spinner<Double> spnPrice;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtNum;

    public void initCar(Car car) {
        txtBrand.setText(car.getBrand());
        txtModel.setText(car.getModel());
        spnPrice.getEditor().setText(String.valueOf(car.getPrice()));
        spnMileage.getEditor().setText(String.valueOf(car.getMileage()));
        checkIsNew.setSelected(car.getIsNew());
        txtNum.setText(String.valueOf(car.getId()));
    }


    @FXML
    void initialize() {

        spnPrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,10000000, 0));
        spnPrice.setEditable(true);

        spnMileage.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,10000000, 0));
        spnMileage.setEditable(true);

    }

    @FXML
    void onCancel(ActionEvent event) {
        checkIsNew.getScene().getWindow().hide();
    }

    @FXML
    void onSave(ActionEvent event) {
        Car car = new Car();
        car.setBrand(txtBrand.getText());
        car.setModel(txtModel.getText());
        car.setPrice(spnPrice.getEditor().getText());
        car.setNew(checkIsNew.isSelected());
        car.setMileage(spnMileage.getEditor().getText());
        car.setId(Integer.parseInt(txtNum.getText()));
        
        //CarService carService = CarService.getINSTANCE();
        DbHelper dbHelper = DbHelper.getInstance();

        if (car.getId() != 0)
            dbHelper.updateCar(car);
        else
            dbHelper.insertCar(car);
        onCancel(null);

    }
}
