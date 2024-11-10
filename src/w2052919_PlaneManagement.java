import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The w2052919_PlaneManagement class represents a program for managing a plane's seating arrangement and tickets.
 */
public class w2052919_PlaneManagement {
    // Constants representing seat availability
    private static final int AVAILABLE = 0;

    // Array representing seat availability
    private static final int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    // Array representing seat prices
    private static final double [][] prices = {
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 180, 180, 180, 180, 180, 180},
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 180, 180, 180, 180},
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 180, 180, 180, 180},
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 180, 180, 180, 180, 180, 180}
    };

    // Arrays representing the capacity of each row
    private static final int[] rowSeatCapacities = {14, 12, 12, 14};

    /**
     * Method to convert a row index to a corresponding row letter.
     * @param row The index of the row.
     * @return The corresponding row letter.
     */
    private static String getRowLetter(int row){
        return String.valueOf((char) ('A' + row));
    }

    /**
     * Method to get the number of seats in a given row.
     * @param row The index of the row.
     * @return The number of seats in the row.
     */
    private static int getNumberOfSeatsInRow(int row) {
        return rowSeatCapacities[row];
    }

    // Array to store ticket objects
    private  static final Ticket[] tickets = new Ticket[52];
    private static int ticketIndex = 0;

    /**
     * The main method to start the Plane Management application.
     */

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to the Plane Management application!");
        System.out.println();
        displayMenu();
    }

    /**
     * Method to display the menu options and handle user input.
     */
    private static void displayMenu() {

        while (true) {

            System.out.println("** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
            System.out.println();
            System.out.println("**               MENU OPTIONS                **");
            System.out.println();
            System.out.println("** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
            System.out.println();
            System.out.println("1. BUY A SEAT");
            System.out.println("2. CANCEL A SEAT");
            System.out.println("3. FIND FIRST AVAILABLE SEAT");
            System.out.println("4. SHOW SEATING PLAN");
            System.out.println("5. PRINT TICKET INFORMATION");
            System.out.println("6. SEARCH FOR A TICKET");
            System.out.println("0. EXIT");
            System.out.println("** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
            System.out.println();

            Scanner input = new Scanner(System.in);
            try {

            System.out.println("PLEASE SELECT AN OPTION TO CONTINUE: ");
            int option = input.nextInt();

                switch (option) {
                case 0:
                    System.out.println("Thank you for using plane management.");
                    return;

                case 1:
                    buy_seat();
                    break;

                case 2:
                    cancel_seat();
                    break;

                case 3:
                    find_first_available();
                    break;

                case 4:
                    show_seating_plan();
                    break;

                case 5:
                    print_ticket_info();
                    break;

                case 6:
                    search_ticket();
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 0 - 6: ");
                }
            }

            catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter and integer.");
                System.out.println();
            }

        }
    }

    /**
     * Method to handle the buying of a seat by a passenger.
     */
    private static void buy_seat(){

        Scanner scanner = new Scanner(System.in);
        char rowLetter;
        int seatNumber;

        try {

        System.out.print("Enter row letter (A, B, C or D): ");
        rowLetter = scanner.next().toUpperCase().charAt(0);
        System.out.print("Enter seat number: ");
        seatNumber = scanner.nextInt();

        int row = rowLetter - 'A';

        if (row < 0 || row >= 4 || seatNumber < 1 || seatNumber > getNumberOfSeatsInRow(row)) {
            System.out.println("Invalid row or seat number. Please try again.");
            return;

        } if (seats[row][seatNumber - 1] == AVAILABLE){
            seats[row][seatNumber - 1] = 1;
            System.out.print("Enter passenger's name: ");
            String name = scanner.next();

            System.out.print("Enter passenger's surname: ");
            String surname = scanner.next();

            System.out.print("Enter passenger's email: ");
            String email = scanner.next();

            // Create a Person object with the provided information
            Person person = new Person(name, surname, email);
            double price = prices[row][seatNumber - 1];

            // Create a Ticket object with the provided information
            Ticket ticket = new Ticket(getRowLetter(row), seatNumber, price, person);
            ticket.saveToFile(); // Save ticket information to a file
            add_ticket(ticket); // Add the ticket to the array
            seats[row][seatNumber - 1] = 1; // Mark the seat as sold
            System.out.println("Seat " + rowLetter + seatNumber + " purchased successfully. Price: £" + price);
        } else {
                System.out.println("Seat " + rowLetter + seatNumber + " is already sold. Please choose another seat.");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter an integer: ");
            System.out.println();
        }
    }

    /**
     * Method to handle the cancellation of a seat by a passenger.
     */
    private static void cancel_seat(){
        Scanner scanner = new Scanner(System.in);
        char rowLetter;
        int seatNumber;

        try {


            System.out.print("Enter row letter (A, B, C or D): ");
            rowLetter = scanner.next().toUpperCase().charAt(0);
            System.out.print("Enter seat number: ");
            seatNumber = scanner.nextInt();

            int row = rowLetter - 'A';

            if (row < 0 || row >= 4 || seatNumber < 1 || seatNumber > getNumberOfSeatsInRow(row)) {
                System.out.println("Invalid row or seat number. Please try again.");
                return;
            }
            if (seats[row][seatNumber - 1] == AVAILABLE) {
                System.out.println("Seat " + rowLetter + seatNumber + " is already available.");
            } else {
                seats[row][seatNumber - 1] = AVAILABLE;
                System.out.println("Seat " + rowLetter + seatNumber + " canceled successfully.");
                for (int i = 0; i < ticketIndex; i++) {
                    Ticket ticket = tickets[i];
                    if (ticket.getRow().equals(String.valueOf(rowLetter)) && ticket.getSeat() == seatNumber) {
                        remove_ticket(ticket);
                        break;
                    }
                }

            }
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter an integer: ");
            System.out.println();
        }

    }

    /**
     * Method to find the first available seat.
     */
    private static void find_first_available() {
            for (int row = 0; row < 4; row++) {
                for (int seat = 0; seat < getNumberOfSeatsInRow(row); seat++) {
                    if (seats[row][seat] == AVAILABLE) {
                        System.out.println("First available seat found at row " + getRowLetter(row) + " seat " + (seat + 1));
                        return;
                    }
                }
            }
            System.out.println("Sorry! No available seats were found.");
    }

    /**
     * Method to display the seating plan.
     */
    private static void show_seating_plan(){
        System.out.println("Seating Plan: ");
        System.out.println("Row    Seat Availability");

        for (int row = 0; row < 4; row++){
            char rowLetter = (char) ('A' + row);
            System.out.print(rowLetter + "     ");

            for (int seat = 0; seat < getNumberOfSeatsInRow(row); seat++){
                if (seats[row][seat] == AVAILABLE) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Method to print information about all sold tickets.
     */
    private static void print_ticket_info(){

        System.out.println("Tickets Sold During the Session:");
        System.out.println("--------------------------------");

        double totalPrice = 0;
        int soldTicketsCount = 0;

        for (int i = 0; i < ticketIndex; i++){
            Ticket ticket = tickets[i];
            String rowLetter = ticket.getRow();
            int seatNumber = ticket.getSeat();
            double price = ticket.getPrice();
            totalPrice += price;
            soldTicketsCount++;
            ticket.printInfo();
        }
        System.out.println("--------------------------------");
        System.out.println("Total Sold Tickets: " + soldTicketsCount);
        System.out.println("Total Amount: £" + totalPrice);
        System.out.println();
    }

    /**
     * Method to search for ticket information based on the seat.
     */
    private static void search_ticket() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter row letter (A, B, C or D): ");
            char rowLetter = scanner.next().toUpperCase().charAt(0);
            System.out.print("Enter the seat number: ");
            int seatNumber = scanner.nextInt();

            int row = rowLetter - 'A';
            if (row < 0 || row >= 4 || seatNumber < 1 || seatNumber > getNumberOfSeatsInRow(row)) {
                System.out.println("Invalid row or seat number. Please try again.");
                return;
            }
            for (int i = 0; i < ticketIndex; i++) {
                Ticket ticket = tickets[i];
                if (ticket.getRow().equals(String.valueOf(rowLetter)) && ticket.getSeat() == seatNumber) {
                    System.out.println("Ticket information:");
                    ticket.printInfo();
                    return;
                }
            }
            System.out.println("No booking was found. This seat is available.");
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter an integer: ");
            System.out.println();
        }
    }

    /**
     * Method to add a ticket to the tickets array.
     * @param ticket The ticket to be added.
     */
    private static void  add_ticket(Ticket ticket){
        if (ticketIndex < tickets.length){
            tickets[ticketIndex] = ticket;
            ticketIndex++;
        } else {
            System.out.println("Cannot add more tickets. Capacity reached.");
        }
    }

    /**
     * Method to remove a ticket from the tickets array.
     * @param ticket The ticket to be removed.
     */
    private static void remove_ticket(Ticket ticket){
        for (int i = 0; i < ticketIndex; i++){
            if (tickets[i] == ticket){
                tickets[i] = null;
                rearrange_tickets_array(i);
                ticketIndex --;
                System.out.println();
                return;
            }
        }
        System.out.println("Ticket not found.");
    }

    /**
     * Method to rearrange the tickets array after removing a ticket.
     * @param index The index of the removed ticket.
     */
    private static void rearrange_tickets_array(int index){
        for (int i = index; i < ticketIndex - 1; i++){
            tickets[i] = tickets[i + 1];
        }
        tickets[ticketIndex - 1] = null;
    }
}