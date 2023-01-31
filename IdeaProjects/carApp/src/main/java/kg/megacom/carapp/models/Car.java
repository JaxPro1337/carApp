package kg.megacom.carapp.models;

public class Car {
    private int id;
    private String brand;
    private String model;
    private double price;
    private boolean isNew;
    private  double mileage;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand.isBlank())
            throw new RuntimeException("Поле (Бренд) не должно быть пустым!");
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.isBlank())
            throw new RuntimeException("Поле (Модель) не должно быть пустым!");
        this.model = model;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(String price) {
        double carPrice;
        try {
            carPrice = Double.parseDouble(price);
        }catch (Exception exception){
            throw  new RuntimeException("Некорректное число!");
        }

        if (carPrice < 0)
            throw new RuntimeException("Цена не должна быть отрицательной!");
        this.price = carPrice;

        setPrice(carPrice);
    }
    public void setPrice (double price){
        if (price < 0)
            throw new RuntimeException("Цена не должна быть отрицательной!");
        this.price = price;
    }

    public boolean getIsNew(){
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        double carMileage;
        try {
            carMileage = Double.parseDouble(mileage);
        }catch (Exception exception){
            throw  new RuntimeException("Некорректное число!");
        }

        setMileage(carMileage);
    }
    public void setMileage (double mileage){
        if (mileage < 0)
            throw new RuntimeException("Пробег не может быть меньше нуля!");
        this.mileage = mileage;
    }
}
