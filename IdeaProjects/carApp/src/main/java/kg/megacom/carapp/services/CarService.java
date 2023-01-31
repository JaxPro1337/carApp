package kg.megacom.carapp.services;

import kg.megacom.carapp.db.DbHelper;
import kg.megacom.carapp.models.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    public List<Car> getCars(){
        DbHelper dbHelper = DbHelper.getInstance();

        return dbHelper.selectCars();
    }
    private List<Car> cars = new ArrayList<>();
    private Car selectedCar;

    public void setSelectedCar(Car car){
        this.selectedCar = car;
    }

    public CarService(){
        Car car = new Car();
        car.setBrand("Brand");
        car.setModel("Model");
        car.setPrice(3000.9);
        car.setNew(true);
        car.setMileage(25000.8);

        cars.add(car);
        car = new Car();
        car.setBrand("Brand");
        car.setModel("Model");
        car.setPrice(3000.9);
        car.setNew(false);
        car.setMileage(25000.8);

        cars.add(car);

    }

    private static CarService INSTANCE;

    public static CarService getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new CarService();
        return INSTANCE;
    }
    public void deleteCar(){
//        DbHelper dbHelper = DbHelper.getInstance();
//        dbHelper.deleteCar();

        //cars.remove(this.selectedCar.getId() - 1);
    }
    public void updateCar(Car car){
        cars.add(car.getId() - 1, car);
        cars.remove(car.getId());
    }


    public void add(Car car){

        DbHelper dbHelper = DbHelper.getInstance();
        dbHelper.insertCar(car);
        //cars.add(car);
    }

    public void showCars(){
        System.out.println(cars.toString());
    }
}
