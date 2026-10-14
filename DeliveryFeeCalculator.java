import java.util.Scanner;

abstract class Delivery {
    double weight, distance;
    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }
    abstract double fee();
    abstract String type();
}

class StandardDelivery extends Delivery {
    StandardDelivery(double w, double d) { super(w, d); }
    double fee() { return 5 + 0.50 * weight + 0.10 * distance; }
    String type() { return "STANDARD"; }
}

class ExpressDelivery extends Delivery {
    ExpressDelivery(double w, double d) { super(w, d); }
    double fee() { return 15 + weight + 0.20 * distance; }
    String type() { return "EXPRESS"; }
}

class InternationalDelivery extends Delivery {
    double customsFee;
    InternationalDelivery(double w, double d, double customsFee) {
        super(w, d);
        this.customsFee = customsFee;
    }
    double fee() { return 25 + 2 * weight + 0.50 * distance + customsFee; }
    String type() { return "INTERNATIONAL"; }
}

public class DeliveryFeeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();
            Delivery delivery;

            if (type.equals("STANDARD"))
                delivery = new StandardDelivery(weight, distance);
            else if (type.equals("EXPRESS"))
                delivery = new ExpressDelivery(weight, distance);
            else
                delivery = new InternationalDelivery(weight, distance, sc.nextDouble());

            double fee = delivery.fee();
            System.out.printf("%s: %.2f%n", delivery.type(), fee);
            total += fee;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
