import java.util.Scanner;

public class MovieTicket {
    // 1. Data members
    private String movieName;
    private double ticketPrice;
    private int numberOfTickets;

    // 2. Parameterized constructor
    public MovieTicket(String movieName, double ticketPrice, int numberOfTickets) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;
    }

    // 3. Calculation methods
    public double calculateTotal() {
        return ticketPrice * numberOfTickets;
    }

    public double calculateDiscount() {
        if (numberOfTickets >= 5) {
            return calculateTotal() * 0.10;
        } else {
            return 0.0;
        }
    }

    public double calculateFinalAmount() {
        return calculateTotal() - calculateDiscount();
    }
    public void displayBill() {
        System.out.println("\n===== CINEMA BOOKING BILL =====");
        System.out.println("Movie Name:        " + movieName);
        System.out.printf("Ticket Price:      $%.2f\n", ticketPrice);
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.printf("Total Amount:      $%.2f\n", calculateTotal());
        System.out.printf("Discount Offered:  $%.2f\n", calculateDiscount());
        System.out.println("--------------------------------");
        System.out.printf("Final Amount Due:  $%.2f\n", calculateFinalAmount());
        System.out.println("================================");
    }

    // 5. THE MAIN METHOD (This must be here to run MovieTicket.java)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading inputs
        System.out.print("Enter Movie Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Ticket Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Number of Tickets: ");
        int tickets = scanner.nextInt();

        // Creating the object
        MovieTicket booking = new MovieTicket(name, price, tickets);

        // Displaying the bill
        booking.displayBill();

        // Closing scanner
        scanner.close();
    }
}