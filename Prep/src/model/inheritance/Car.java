package model.inheritance;

public class Car extends Vehicle {
    @Override
    public void honk() {
        System.out.println("Car Honk!");
    }
}