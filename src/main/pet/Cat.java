package main.pet;

/**
 * Cat - a class that represents a cat, extending the abstract class: AbstractPet (which implements Pet interface)
 *
 * @author Billy peters
 * 21/02/2024
 */
public final class Cat extends AbstractPet{
    /**
     * Cat() - constructor to create a Cat object
     */
    Cat() {
        super("cat","Feed two times a day");
    }

    /**
     * Cat(Cat) - a constructor for copying a Cat object
     * @param pet - the object to be copied
     */
    Cat (Cat pet) { //copy constructor
        super(pet);
    }
}
