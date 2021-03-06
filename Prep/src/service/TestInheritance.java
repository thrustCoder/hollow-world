package service;

import model.inheritance.Car;
import model.inheritance.Vehicle;
import util.Printer;

public class TestInheritance {

    public static void testHonk() {
        Vehicle myCar = new Car(4, "Honda");

        // Prints:
        // Vehicle Move!
        // Car Honk!
        myCar.move();

        // Prints:
        // Honda
        Printer.print(((Car) myCar).getManufacturer());
    }
}
