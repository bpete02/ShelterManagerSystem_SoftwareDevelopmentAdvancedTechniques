package main.customer;

/**
 * Name - A class representing a customer's name, containing their first and last name
 *
 * @author Newcastle University Student ID: 23055481
 * 21/02/2024
 */
public final class Name {
    private final String firstName;
    private final String lastName;

    /**
     * Name(String, String) - public constructor creating Name object
     * @param first - customer's first name
     * @param second - customer's last name
     */
    public Name(String first, String second){
        this.firstName = first;
        this.lastName = second;
    }

    /**
     * getFirstName() - retrieves Customer's first name
     * @return String of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getFirstName() - retrieves Customer's last name
     * @return String of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * equals(Name) - compares this Name object to another Name object
     * @return true if both first and last names are equal.
     */
    public boolean equals(Name otherName){
        return (this.firstName.equals(otherName.getFirstName())&&this.lastName.equals(otherName.getLastName()));
    }
}
