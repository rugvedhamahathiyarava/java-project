import java.util.ArrayList;
import java.util.Scanner;

/*
 * SIMPLE MONTHLY EXPENSE & BUDGET ANALYZER (no files)
 * CO1 - Data types           : int, double, char, boolean, String
 * CO2 - Conditions & loops   : if-else, switch, do-while, for, for-each
 * CO3 - Arrays & methods     : categories[], categoryTotals[], addIncome(), addExpense(), ...
 * CO4 - OOP & inheritance    : Transaction -> Income, Expense
 * CO5 - Strings              : concatenation, toLowerCase(), contains(), equals()
 * CO6 - Collection framework : ArrayList<Transaction>
 */

// ---------- CO4: Base class (encapsulation) ----------
class Transaction {
    private double amount;
    private String description;

    Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    double getAmount() { return amount; }
    String getDescription() { return description; }

    void display() {
        System.out.println(description + " : " + amount);
    }
}

// ---------- CO4: Derived class 1 ----------
class Income extends Transaction {
    String source;

    Income(double amount, String description, String source) {
        super(amount, description);
        this.source = source;
    }

    @Override
    void display() {   // polymorphism
        System.out.println("INCOME  | " + getDescription() + " | " + getAmount() + " | from " + source);
    }
}

// ---------- CO4: Derived class 2 ----------
class Expense extends Transaction {
    String category;

    Expense(double amount, String description, String category) {
        super(amount, description);
        this.category = category;
    }

    @Override
    void display() {   // polymorphism
        System.out.println("EXPENSE | " + getDescription() + " | " + getAmount() + " | " + category);
    }
}

public class SimpleExpenseAnalyzer2 {

    // CO6: ArrayList holds both Income and Expense objects
    static ArrayList<Transaction> transactions = new ArrayList<>();

    // CO3: arrays
    static String[] categories = {"Food", "Travel", "Bills", "Other"};
    static double[] categoryTotals = new double[4];

    // CO1: data types
    static double budget = 10000.0;     // double
    static int count = 0;               // int
    static char type = 'E';             // char
    static boolean overBudget = false;  // boolean
    static String title = "Expense Analyzer";   // String

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== " + title + " =====");

        int choice;
        do {                                           // CO2: loop
            System.out.println("\n1.Add Income  2.Add Expense  3.Search  4.Report  0.Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {                          // CO2: switch
                case 1: addIncome(); break;
                case 2: addExpense(); break;
                case 3: search(); break;
                case 4: report(); break;
                case 0: System.out.println("Bye!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    // ---------- CO3: methods ----------
    static void addIncome() {
        type = 'I';
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Source: ");
        String source = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {                             // CO2: validation
            System.out.println("Amount must be positive!");
        } else {
            transactions.add(new Income(amount, desc, source));   // CO6: add()
            count++;
            System.out.println("Income added.");
        }
    }

    static void addExpense() {
        type = 'E';
        System.out.print("Description: ");
        String desc = sc.nextLine();

        for (int i = 0; i < categories.length; i++) {  // CO2: for loop + CO3: array
            System.out.println((i + 1) + ". " + categories[i]);
        }
        System.out.print("Category number: ");
        int c = Integer.parseInt(sc.nextLine());
        if (c < 1 || c > categories.length) {
            System.out.println("Invalid category!");
            return;
        }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }

        transactions.add(new Expense(amount, desc, categories[c - 1]));
        count++;
        System.out.println("Expense added.");
    }

    static void search() {
        System.out.print("Enter keyword: ");
        String key = sc.nextLine().toLowerCase();      // CO5: String method
        boolean found = false;

        for (Transaction t : transactions) {           // CO6: for-each
            if (t.getDescription().toLowerCase().contains(key)) {   // CO5: contains()
                t.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found.");
        }
    }

    static void report() {
        double totalIncome = 0, totalExpense = 0;
        categoryTotals = new double[categories.length];

        for (Transaction t : transactions) {
            if (t instanceof Income) {
                totalIncome += t.getAmount();
            } else if (t instanceof Expense) {
                totalExpense += t.getAmount();
                Expense e = (Expense) t;
                for (int i = 0; i < categories.length; i++) {
                    if (categories[i].equals(e.category)) {   // CO5: equals()
                        categoryTotals[i] += e.getAmount();
                    }
                }
            }
        }

        System.out.println("\n----- MONTHLY REPORT -----");
        System.out.println("Total records : " + count);
        System.out.println("Total Income  : " + totalIncome);      // CO5: concatenation
        System.out.println("Total Expense : " + totalExpense);
        System.out.println("Balance       : " + (totalIncome - totalExpense));
        for (int i = 0; i < categories.length; i++) {
            System.out.println(categories[i] + " : " + categoryTotals[i]);
        }

        overBudget = totalExpense > budget;
        if (overBudget) {
            System.out.println("WARNING: Over budget by " + (totalExpense - budget));
        } else {
            System.out.println("Within budget. Remaining: " + (budget - totalExpense));
        }
    }
}
