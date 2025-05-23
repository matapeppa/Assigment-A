import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Order {
    String customerName;
    double totalAmount;
    LocalDate date;

    public Order(String customerName, double totalAmount, LocalDate date) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public String toCSV() {
        return customerName + "," + totalAmount + "," + date;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Amount: " + totalAmount + ", Date: " + date;
    }
}

public class CoffeeShopApp {
    private static final List<Order> orders = new ArrayList<>();
    private static final String FILE_NAME = "orders.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        loadOrdersFromCSV();

        do {
            System.out.println("\n1. Add Order");
            System.out.println("2. View orders of a date");
            System.out.println("3. View total amount of a customer");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addOrder(scanner);
                case 2 -> viewOrdersOfDate(scanner);
                case 3 -> viewTotalOfCustomer(scanner);
            }
        } while (choice != 0);

        saveOrdersToCSV();
    }

    private static void addOrder(Scanner scanner) {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Total amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        orders.add(new Order(name, amount, date));
        System.out.println("Order added.");
    }

    private static void viewOrdersOfDate(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        for (Order o : orders) {
            if (o.date.equals(date)) {
                System.out.println(o);
            }
        }
    }

    private static void viewTotalOfCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        double total = 0;
        for (Order o : orders) {
            if (o.customerName.equalsIgnoreCase(name)) {
                total += o.totalAmount;
            }
        }
        System.out.println("Total amount spent by " + name + ": " + total);
    }

    private static void loadOrdersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                orders.add(new Order(parts[0], Double.parseDouble(parts[1]), LocalDate.parse(parts[2])));
            }
        } catch (IOException e) {
            System.out.println("No previous orders found.");
        }
    }

    private static void saveOrdersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Order o : orders) {
                bw.write(o.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving orders.");
        }
    }
}
