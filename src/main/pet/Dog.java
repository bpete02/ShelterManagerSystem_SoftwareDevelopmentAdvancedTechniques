package main.pet;

/**
 * Dog - a class that represents a dog, extending the abstract class: AbstractPet (which implements Pet interface)
 * Dog implements Trainable interface and NeedsGarden marker interface.
 *
 * @author Newcastle University Student ID: 23055481
 * 21/02/2024
 */
public final class Dog extends AbstractPet implements Trainable,NeedsGarden{
    private boolean trained;

    /**
     * Dog() - constructor to create a dog object
     */
    Dog()  {
        super("dog","feed three times a day, walk once a day");
        this.trained = false;
    }

    /**
     * Dog(Dog) - a constructor for copying a Dog object
     * @param pet - the object to be copied
     */
    Dog (Dog pet) { //copy constructor
        super(pet);
        this.trained = pet.getTrained();
    }

    /**
     * getTrained() - retrieves if Dog object trained field
     * @return true if dog is trained, false otherwise
     */
    @Override
    public boolean getTrained(){
        return trained;
    }

    /**
     * setTrained(boolean) - changes dog trained field to parameter value.
     * @param trained true if pet is trained, false otherwise
     */
    @Override
    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    /**
     * toString() - converts object fields to a single string representation
     * @return String containing: AbstractPet.toString() and trained field
     */
    @Override
    public String toString() {
        return (super.toString()+ ","+ trained);
    }

}
