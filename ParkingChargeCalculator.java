import java.util.Scanner;

abstract class Vehicle {
    int hours;
    Vehicle(int hours) { this.hours = hours; }
    abstract double charge();
    abstract String type();
}

class Bike extends Vehicle {
    Bike(int hours) { super(hours); }
    double charge() { return hours * 10.0; }
    String type() { return "BIKE"; }
}

class Car extends Vehicle {
    Car(int hours) { super(hours); }
    double charge() { return 30 + (hours - 1) * 20.0; }
    String type() { return "CAR"; }
}

class Truck extends Vehicle {
    Truck(int hours) { super(hours); }
    double charge() { return Math.max(100, hours * 50.0); }
    String type() { return "TRUCK"; }
}

public class ParkingChargeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            int hours = sc.nextInt();
            Vehicle vehicle;

            if (type.equals("BIKE"))
                vehicle = new Bike(hours);
            else if (type.equals("CAR"))
                vehicle = new Car(hours);
            else
                vehicle = new Truck(hours);

            double amount = vehicle.charge();
            System.out.printf("%s: %.2f%n", vehicle.type(), amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
