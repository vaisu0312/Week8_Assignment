import java.util.Scanner;

abstract class EmployeeBonus {
    String name;
    double salary;
    EmployeeBonus(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    abstract double bonus();
}

class FullTimeEmployee extends EmployeeBonus {
    FullTimeEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return salary * 0.10; }
}

class PartTimeEmployee extends EmployeeBonus {
    PartTimeEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return salary * 0.05; }
}

class InternEmployee extends EmployeeBonus {
    InternEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return 2000; }
}

public class FestivalBonusCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();
            double salary = sc.nextDouble();
            EmployeeBonus employee;

            if (type.equals("FULLTIME"))
                employee = new FullTimeEmployee(name, salary);
            else if (type.equals("PARTTIME"))
                employee = new PartTimeEmployee(name, salary);
            else
                employee = new InternEmployee(name, salary);

            double bonus = employee.bonus();
            System.out.printf("%s: %.2f%n", name, bonus);
            total += bonus;
        }

        System.out.printf("Total Bonus: %.2f%n", total);
        sc.close();
    }
}
