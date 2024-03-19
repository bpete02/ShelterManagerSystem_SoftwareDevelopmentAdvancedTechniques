package main.customer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CustomerNumber - A class representing a customer's unique ID, formed from their initial, unique serial number, and the date of issue of their record.
 *
 * @author Billy Peters
 * 21/02/2024
 */
public final class CustomerNumber {
    private static final Set<String> FIRSTCOMPONENTS = new HashSet<>();
    private static final Set<String> CUSTOMERNUMBERS = new HashSet<>();
    private final String firstComponent;
    private final String secondComponent;

    /**
     * CustomerNumber(String, Date) - Private constructor
     * @param firstName - first name of customer, which is indexed to get the first initial for the first component of the unique ID
     * @param issue - date of issue of the record, which is used for the second component of the unique ID.
     * @throws Exception if it cannot create a unique ID. Should not occur but added in case.
     */
    private CustomerNumber(String firstName, Date issue) throws Exception {
        int countExistingIds=0;
        Calendar calIssue = Calendar.getInstance();
        calIssue.setTime(issue);
        int issueMonth = calIssue.get(Calendar.MONTH)+1; //must add 1 to Calendar.Month as January is represented by a 0 in Calendar.
        int issueYear = calIssue.get(Calendar.YEAR);
        this.secondComponent = String.valueOf(issueMonth)+String.valueOf(issueYear);

        char firstInitial = firstName.toUpperCase().charAt(0);
        for (String current : FIRSTCOMPONENTS) {
            if (current.charAt(0) == (firstInitial)) {
                countExistingIds += 1;
            }
        }

        this.firstComponent = firstInitial + Integer.toString(countExistingIds+1);
        if(!(FIRSTCOMPONENTS.add(firstComponent))){
            throw new Exception("CustomerNumber first component failed: "+firstComponent+ "\t"+FIRSTCOMPONENTS);
        }

        String fullID = this.firstComponent+ "." +this.secondComponent;
        if(!CUSTOMERNUMBERS.add(fullID)){
            FIRSTCOMPONENTS.remove(firstComponent);
            throw new Exception("CustomerNumber completeId is not unique: "+fullID+"\t"+CUSTOMERNUMBERS);
        }
    }

    /**
     * getInstance(String,Date) - creates a new CustomerNumber, by calling private constructor
     * @param firstName - first name of customer, which is indexed to get the first initial for the first component of the unique ID
     * @param issue - date of issue of the record, which is used for the second component of the unique ID.
     * @return unique CustomerNumber object, or null if an exception is thrown when creating the customer number or if parameters are null or empty.
     */
    public static CustomerNumber getInstance(String firstName, Date issue){
        if(firstName == null || issue == null || firstName.isBlank()){
            return null;
        }else{
            firstName = firstName.trim();
            firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase(); // ensure name is formatted correctly (uppercase first letter)

        }

        try{
            return new CustomerNumber(firstName,issue);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * getFirstComponent() - Retrieves the first component of the ID (The first initial + serial number)
     * @return String firstComponent field
     */
    public String getFirstComponent(){
        return this.firstComponent;
    }
    /**
     * getSecondComponent() - Retrieves the second component of the ID (the month and year of issue)
     * @return String secondComponent field
     */
    public String getSecondComponent(){
        return secondComponent;
    }

    /**
     * toString() retrieves string representation of the ID
     * @return String firstComponent.secondComponent
     */
    @Override
    public String toString(){
        return (this.firstComponent+ "." +this.secondComponent);
    }
}
