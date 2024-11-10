/**
 * The Person class represents a person with a name, surname, and email.
 */
public class Person {
    private String name; // The name of the person
    private String surname; // The surname of the person
    private String email; // The email address of the person

    /**
     * Constructs a new Person object with the given parameters.
     * @param name The name of the person.
     * @param surname The surname of the person.
     * @param email The email address of the person.
     */
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Gets the name of the person.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the surname of the person.
     * @return The surname of the person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets the email address of the person.
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the name of the person.
     * @param name The name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the surname of the person.
     * @param surname The surname of the person.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets the email address of the person.
     * @param email The email address of the person.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Prints the information of the person.
     */
    public void printPersonInfo(){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("email: " + email);
    }
}