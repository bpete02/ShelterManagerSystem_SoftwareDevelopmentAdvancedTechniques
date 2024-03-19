package main.pet;

import java.util.Set;
import java.util.TreeSet;

/**
 * PetID - class representing a Pet object's unique ID
 *
 * @author Billy Peters
 * 21/02/2024
 */
public final class PetID {
    private static final Set<String> IDS = new TreeSet<>();
    private final char singleLetterIdComponent;
    private final int twoDigitIdComponent;

    /**
     * PetID() - Private constructor
     * @throws Exception if it cannot create a unique ID. Occurs when the number of PetIDs created reaches 2574, the maximum number of IDs possible using this ID format.
     */
    PetID() throws Exception {
        char singleLetter = 'a';

        int countExistingIds=0;
        for(String id: IDS){
            if(id.substring(0,1).equals(Character.toString(singleLetter))){
                countExistingIds += 1;
                if(countExistingIds == 99){
                    if(singleLetter == 'z'){
                        throw new Exception("Cannot create new PetIDs, as the maximum number has already been reached");
                    }else {
                        singleLetter += 1;
                        countExistingIds = 0;
                    }
                }
            }
        }
        this.singleLetterIdComponent = singleLetter;
        this.twoDigitIdComponent = countExistingIds+1;
        if(!IDS.add(Character.toString(singleLetterIdComponent) + String.format("%02d", twoDigitIdComponent))){
            throw new Exception("Id already exists");
        }
    }

    /**
     * getInstance(String,Date) - creates a new PetID, by calling private constructor
     * @return unique PetID object, or null if an exception is thrown when creating the PetID (ie if maximum number is reached).
     */
    public static PetID getInstance(){
        try {
            return new PetID();
        } catch (Exception e){
            return null;
        }
    }

    /**
     * getSingleLetterIdComponent() - Retrieves the first component of the ID
     * @return char singleLetterIdComponent.
     */
    public char getSingleLetterIdComponent(){
        return singleLetterIdComponent;
    }

    /**
     * getTwoDigitIdComponent() - Retrieves the two digit component of the ID
     * @return int twoDigitIdComponent.
     */
    public int getTwoDigitIdComponent(){
        return twoDigitIdComponent;
    }

    /**
     * toString() retrieves string representation of the ID
     * @return String singleLetterIdComponent+twoDigitIdComponent e.g. c03
     */
    @Override
    public String toString() {
        return (singleLetterIdComponent + String.format("%02d", twoDigitIdComponent));
    }
}

