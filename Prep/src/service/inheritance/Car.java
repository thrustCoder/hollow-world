package service.inheritance;

public class Car extends Vehicle {
    @Override
    public void honk() {
        System.out.println("Car Honk!");
    }
}