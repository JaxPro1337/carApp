package kg.megacom.carapp.db;

import kg.megacom.carapp.models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private static DbHelper INSTANCE;
    private Car selectedCar;

    public void setSelectedCar(Car car){
        this.selectedCar = car;
    }

    public  static DbHelper getInstance(){
        if (INSTANCE == null)
            INSTANCE = new DbHelper();
        return  INSTANCE;
    }

    private Connection connect(){
        Connection connection = null;

        String url = "jdbc:sqlite:D:/Курсы/Базы данных/cardb.db";
        System.out.println("connected");

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
        return connection;

    }

    public void updateCar(Car car){
        String query = "update cars set brand = ? , model = ?, is_new = ?, price = ?, mileage = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(6, selectedCar.getId());
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setBoolean(3, car.getIsNew());
            ps.setDouble(4, car.getPrice());
            ps.setDouble(5, car.getMileage());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void deleteCar(){
        String query = "delete from cars where id = ?";

        try(Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setInt(1, selectedCar.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCar(Car car){
        String query = "insert into cars (brand, model, is_new, price, mileage) values (?, ?, ?, ?, ?)";

        try(Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setBoolean(3, car.getIsNew());
            ps.setDouble(4, car.getPrice());
            ps.setDouble(5, car.getMileage());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Car> selectCars(){

        List<Car> cars = new ArrayList<>();
        String query = "select id, brand, model, is_new, price, mileage from cars";


        try (Connection connection = connect();
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setPrice(resultSet.getDouble("price"));
                car.setMileage(resultSet.getDouble("mileage"));
                car.setNew(resultSet.getBoolean("is_new"));

                cars.add(car);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;

    }


}
