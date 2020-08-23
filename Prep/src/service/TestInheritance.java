package service;

import model.inheritance.Car;
import model.inheritance.Vehicle;

public class TestInheritance {

    public static void testHonk() {
        Vehicle myCar = new Car();
        myCar.move();
    }
}
