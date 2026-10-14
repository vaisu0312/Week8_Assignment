import java.time.LocalDate;
import java.util.Scanner;

abstract class Plan {
    String name;
    LocalDate startDate;
    Plan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }
    abstract int validityDays();
    LocalDate renewalDate() {
        return startDate.plusDays(validityDays());
    }
}

class BasicPlan extends Plan {
    BasicPlan(String name, LocalDate date) { super(name, date); }
    int validityDays() { return 30; }
}

class StandardPlan extends Plan {
    StandardPlan(String name, LocalDate date) { super(name, date); }
    int validityDays() { return 90; }
}

class PremiumPlan extends Plan {
    PremiumPlan(String name, LocalDate date) { super(name, date); }
    int validityDays() { return 365; }
}

public class StreamingRenewalReminder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\\\s+");
            String type = data[0].toUpperCase();
            String name = data[1];
            LocalDate date = LocalDate.parse(data[2]);
            Plan plan;

            if (type.equals("BASIC"))
                plan = new BasicPlan(name, date);
            else if (type.equals("STANDARD"))
                plan = new StandardPlan(name, date);
            else
                plan = new PremiumPlan(name, date);

            System.out.println(plan.name + ": " + plan.renewalDate());
        }

        sc.close();
    }
}
