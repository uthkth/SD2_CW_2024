import java.io.FileWriter;
import java.io.IOException;

/**
 * The Ticket class represents a ticket for a seat on the plane.
 */
public class Ticket {
    private String row; // The row of the seat
    private int seat; // The seat number
    private double price; // The price of the ticket
    private Person person; // The person associated with the ticket


    /**
     * Constructs a new Ticket object with the given parameters.
     * @param row The row of the seat.
     * @param seat The seat number.
     * @param price The price of the ticket.
     * @param person The person associated with the ticket.
     */
    public Ticket(String row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    /**
     * Gets the row of the seat.
     * @return The row of the seat.
     */
    public String getRow(){
        return row;
    }

    /**
     * Sets the row of the seat.
     * @param row The row of the seat.
     */
    public void setRow(String row){
        this.row = row;
    }

    /**
     * Gets the seat number.
     * @return The seat number.
     */
    public int getSeat(){
        return seat;
    }

    /**
     * Sets the seat number.
     * @param seat The seat number.
     */
    public void setSeat(int seat){
        this.seat = seat;
    }

    /**
     * Gets the price of the ticket.
     * @return The price of the ticket.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Sets the price of the ticket.
     * @param price The price of the ticket.
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Gets the person associated with the ticket.
     * @return The person associated with the ticket.
     */
    public Person getPerson(){
        return person;
    }

    /**
     * Sets the person associated with the ticket.
     * @param person The person associated with the ticket.
     */
    public void setPerson(Person person){
        this.person = person;
    }

    /**
     * Prints the ticket information along with passenger information.
     */
    public void printInfo(){
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println();
        System.out.println("Passenger Information:");
        System.out.println("--------------------------------");
        person.printPersonInfo();
        System.out.println();
    }

    /**
     * Saves the ticket information to a file.
     */
    public void saveToFile() {
        try {
            String filename = row + seat + ".txt";
            FileWriter writer = new FileWriter(filename);
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: £" + price + "\n");
            writer.write("Passenger Information: \n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
            System.out.println("Ticket information saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information to the file.");
            e.printStackTrace();
        }
    }

}
