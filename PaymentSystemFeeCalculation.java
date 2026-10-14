import java.util.Scanner;

abstract class Payment {
    double amount;
    Payment(double amount) { this.amount = amount; }
    abstract double adjustedAmount();
    abstract String type();
}

class CardPayment extends Payment {
    CardPayment(double amount) { super(amount); }
    double adjustedAmount() { return amount * 1.02; }
    String type() { return "CARD"; }
}

class WalletPayment extends Payment {
    WalletPayment(double amount) { super(amount); }
    double adjustedAmount() { return amount * 1.01; }
    String type() { return "WALLET"; }
}

class BankTransfer extends Payment {
    BankTransfer(double amount) { super(amount); }
    double adjustedAmount() { return amount; }
    String type() { return "BANKTRANSFER"; }
}

public class PaymentSystemFeeCalculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double amount = sc.nextDouble();
            Payment payment;

            if (type.equals("CARD"))
                payment = new CardPayment(amount);
            else if (type.equals("WALLET"))
                payment = new WalletPayment(amount);
            else
                payment = new BankTransfer(amount);

            double adjusted = payment.adjustedAmount();
            System.out.printf("%s: %.2f%n", payment.type(), adjusted);
            total += adjusted;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
