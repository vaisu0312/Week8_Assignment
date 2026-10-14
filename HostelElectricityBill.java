import java.util.Scanner;

abstract class Room {
    int units;
    Room(int units) { this.units = units; }
    abstract double bill();
    abstract String type();
}

class SingleRoom extends Room {
    SingleRoom(int units) { super(units); }
    double bill() { return units * 8.0; }
    String type() { return "SINGLE"; }
}

class SharedRoom extends Room {
    int occupants;
    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }
    double bill() { return units * 6.0 / occupants; }
    String type() { return "SHARED"; }
}

class AcRoom extends Room {
    AcRoom(int units) { super(units); }
    double bill() { return units * 10.0 + 200; }
    String type() { return "AC"; }
}

public class HostelElectricityBill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            int units = sc.nextInt();
            Room room;

            if (type.equals("SINGLE"))
                room = new SingleRoom(units);
            else if (type.equals("SHARED"))
                room = new SharedRoom(units, sc.nextInt());
            else
                room = new AcRoom(units);

            double amount = room.bill();
            System.out.printf("%s: %.2f%n", room.type(), amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
