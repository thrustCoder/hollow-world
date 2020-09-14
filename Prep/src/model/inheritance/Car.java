package model.inheritance;

public class Car extends Vehicle {

    private String manufacturer;

    // paremeterized constructor of child class.
    public Car(int numberOfWheels, String manufacturer) {
        super(numberOfWheels);
        this.manufacturer = manufacturer;
    }

    // default constructor of child class
    public Car() {
        super();
    }

    // getter
    public String getManufacturer() {
        return this. manufacturer;
    }

    // setter
    public void setManufacturer(final String value) {
        this.manufacturer = value;
    }

    @Override
    public void honk() {
        System.out.println("Car Honk!");
    }
}