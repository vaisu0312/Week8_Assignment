import java.time.LocalDate;
import java.util.Scanner;

abstract class LibraryItem {
    String title;
    LibraryItem(String title) { this.title = title; }
    abstract int loanDays();
    LocalDate dueDate() {
        return LocalDate.of(2023, 10, 26).plusDays(loanDays());
    }
}

class Book extends LibraryItem {
    Book(String title) { super(title); }
    int loanDays() { return 14; }
}

class DVD extends LibraryItem {
    DVD(String title) { super(title); }
    int loanDays() { return 7; }
}

class Magazine extends LibraryItem {
    Magazine(String title) { super(title); }
    int loanDays() { return 3; }
}

public class LibraryItemDueDateCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace).toUpperCase();
            String title = line.substring(firstSpace + 1).trim();
            if (title.startsWith("\"") && title.endsWith("\""))
                title = title.substring(1, title.length() - 1);

            LibraryItem item;
            if (type.equals("BOOK"))
                item = new Book(title);
            else if (type.equals("DVD"))
                item = new DVD(title);
            else
                item = new Magazine(title);

            System.out.println(item.title + ": " + item.dueDate());
        }

        sc.close();
    }
}
