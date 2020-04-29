package service.inheritance;

public class Vehicle {

    public void honk() {
        System.out.println("Vehicle Honk!");
    }

    public void move() {
        System.out.println("Vehicle Move!");
        honk();
    }
}
