package model.inheritance;

public class Vehicle {
    private Integer numberOfWheels;

    // paremeterized constructor of parent class.
    public Vehicle(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    // default constructor
    public Vehicle() {}

    // getter
    public Integer getNumberOfWheels() {
        return this.numberOfWheels;
    }

    public void honk() {
        System.out.println("Vehicle Honk!");
    }

    public void move() {
        System.out.println("Vehicle Move!");
        honk();
    }
}
