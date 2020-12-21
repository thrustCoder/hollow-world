package model.inheritance;

// Keyword abstract is redundant here, but probably considered a good practice,
// although I haven't seen it being used that often in Amazon.
// It would have been useful if honk was supposed to be a no body method, then we could have used:
// public abstract void honk() {}
public abstract class Vehicle {
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
