import java.util.Scanner;

abstract class Transport {
    double distance;
    Transport(double distance) { this.distance = distance; }
    abstract double fare();
    abstract String type();
}

class Bus extends Transport {
    Bus(double distance) { super(distance); }
    double fare() { return Math.min(10, 2 + 0.10 * distance); }
    String type() { return "BUS"; }
}

class Train extends Transport {
    Train(double distance) { super(distance); }
    double fare() { return 3 + 0.15 * distance; }
    String type() { return "TRAIN"; }
}

class Metro extends Transport {
    double peakFactor;
    Metro(double distance, double peakFactor) {
        super(distance);
        this.peakFactor = peakFactor;
    }
    double fare() { return (1.50 + 0.20 * distance) * peakFactor; }
    String type() { return "METRO"; }
}

public class PublicTransportFareCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double distance = sc.nextDouble();
            Transport transport;

            if (type.equals("BUS"))
                transport = new Bus(distance);
            else if (type.equals("TRAIN"))
                transport = new Train(distance);
            else
                transport = new Metro(distance, sc.nextDouble());

            double fare = transport.fare();
            System.out.printf("%s: %.2f%n", transport.type(), fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
