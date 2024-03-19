package main.customer;

import java.util.Date;
import java.util.Calendar;

/**
 * CustomerRecord - A class representing a customer, containing their name, unique ID, date of birth, date of issue of record, and if they have a garden
 *
 * @author Newcastle University Student ID: 23055481
 * 21/02/2024
 */
public final class CustomerRecord{
    private final Name name;

    private final CustomerNumber id;

    private final Date dob;

    private final Date issue;

    private final boolean hasGarden;


    private CustomerRecord(String firstName, String lastName, Date dob, Boolean hasGarden) {
        this.name = new Name(firstName,lastName);
        this.dob = dob;
        this.hasGarden = hasGarden;
        Calendar cal = Calendar.getInstance();
        this.issue = cal.getTime();
        this.id = CustomerNumber.getInstance(firstName,issue);
    }

    /**
     * CustomerRecord(String, String, Date, Boolean) - creates and returns a new customer record object.
     * @param firstName - customer's first name
     * @param lastName - customer's second name
     * @param dob - customer's date of birth
     * @param hasGarden - true if customer has garden
     * @return CustomerRecord object or null if parameters are null or empty
     */
    public static CustomerRecord getInstance(String firstName, String lastName, Date dob, Boolean hasGarden){
        if(firstName == null || lastName == null || dob == null || hasGarden == null || firstName.isBlank() || lastName.isBlank()){
            return null;
        }else{ // ensure correct formatting of first and last name
            firstName = firstName.trim();
            firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();

            lastName = lastName.trim();
            lastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1).toLowerCase();
        }

        CustomerRecord record = new CustomerRecord(firstName,lastName,dob,hasGarden);
        if(record.getCustomerNumber()==null){
            return null;
        }else{
            return record;
        }
    }

    /**
     * getName() - retrieves CustomerRecord object's name field
     * @return Name object
     */
    public Name getName() {
        return this.name;
    }

    /**
     * getDob() - retrieves CustomerRecord object's dob field
     * @return Date object
     */
    public Date getDob() {
        return new Date(this.dob.getTime()); // return copy of date object as Date class is mutable
    }

    /**
     * getIssue() - retrieves CustomerRecord object's (date of) issue field
     * @return Date object
     */
    public Date getIssue() {
        return new Date(this.issue.getTime()); //return copy of date object as Date class is mutable
    }

    /**
     * getCustomerNumber() - retrieves CustomerRecord object's (unique) id field.
     * @return CustomerNumber object
     */
    public CustomerNumber getCustomerNumber() {
        return this.id;
    }

    /**
     * getHasGarden() - retrives CustomerRecord object's hasGarden field
     * @return true if CustomerRecord has a garden.
     */
    public boolean getHasGarden() {
        return hasGarden;
    }

    /**
     * calcAge() - calculates and returns customer's age
     * @return int representing how old customer is in years.
     */
    public int calcAge(){
        Calendar currentCal = Calendar.getInstance();
        Date currentDate = currentCal.getTime();
        currentCal.setTime(currentDate);

        Calendar dobCal  = Calendar.getInstance();
        dobCal.setTime(this.dob);

        int age = (currentCal.get(Calendar.YEAR)) - (dobCal.get(Calendar.YEAR)); //calculating current age example found here: https://www.javatpoint.com/java-calculate-age  Original Author: JavaTpoint Modifying Author: 230554812
        if ( (dobCal.get(Calendar.MONTH) > currentCal.get(Calendar.MONTH)) || (dobCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH) && dobCal.get(Calendar.DAY_OF_MONTH) > currentCal.get(Calendar.DAY_OF_MONTH))){
            age--; //decrease by 1
        }

        return age;
    }

    /**
     * toString() - converts object fields to a single string representation
     * @return String containing: CustomerNumber (unique ID), Name (customer's name)
     */
    @Override
    public String toString() {
        return (this.id.toString()+","+this.name.toString());
    }
}

