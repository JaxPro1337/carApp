package kg.megacom.carapp.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.carapp.HelloApplication;
import kg.megacom.carapp.db.DbHelper;
import kg.megacom.carapp.models.Car;
import kg.megacom.carapp.services.CarService;

import java.io.IOException;
import java.util.List;

public class MainController {

    //private CarService carService = CarService.getINSTANCE();
    private DbHelper dbHelper = DbHelper.getInstance();

    @FXML
    private TableColumn<?, ?> colmBrand;

    @FXML
    private TableColumn<Car, Boolean> colmIsNew;

    @FXML
    private TableColumn<?, ?> colmMileage;

    @FXML
    private TableColumn<?, ?> colmModel;

    @FXML
    private TableColumn<?, ?> colmNum;

    @FXML
    private TableColumn<?, ?> colmPrice;

    @FXML
    private TableView<Car> tbCars;

    @FXML
    void initialize() {

        initColumns();

        refreshTable();

    }

    private void initColumns() {
        colmBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colmModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        colmNum.setCellValueFactory(new PropertyValueFactory<>("id"));

        colmIsNew.setCellFactory(column -> new CheckBoxTableCell<>());
        colmIsNew.setCellValueFactory(cellData -> {
            Car cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(cellValue.getIsNew());

            property.addListener((observableValue, oldValue, newValue) -> cellValue.setNew(newValue));

            return property;
        });
    }


    @FXML
    void onCreate(ActionEvent event) {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("car-edit-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

            refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onDelete(ActionEvent event) {
        Car selectedCar = tbCars.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("confirm-view.fxml"));
        if (selectedCar == null)
            return;
        dbHelper.setSelectedCar(selectedCar);

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refreshTable();
    }
    @FXML
    void onUpdate(ActionEvent event) {
        Car selectedCar = tbCars.getSelectionModel().getSelectedItem();

        if (selectedCar == null)
            return;
        dbHelper.setSelectedCar(selectedCar);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("car-edit-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            CarEditController carEditController = loader.getController();
            carEditController.initCar(selectedCar);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setResizable(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refreshTable();
        System.out.println("Hello world!!!");
    }
    @FXML
    void onCopy(ActionEvent event) {
        Car selectedCar = tbCars.getSelectionModel().getSelectedItem();


        if (selectedCar == null)
            return;
        Stage stage = new Stage();
        selectedCar.setId(0);
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("car-edit-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            CarEditController carEditController = loader.getController();
            carEditController.initCar(selectedCar);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setResizable(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refreshTable();
    }

    private void refreshTable() {
        List<Car> cars = dbHelper.selectCars();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.setId(i + 1);
        }
        tbCars.setItems(FXCollections.observableList(dbHelper.selectCars()));
    }
}
