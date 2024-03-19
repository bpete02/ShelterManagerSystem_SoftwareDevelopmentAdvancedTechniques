package main.pet;

/**
 * AbstractPet - an abstract class that acts as am object factory for classes that implement the Pet interface
 *
 * @author Billy Peters
 * 21/02/2024
 */
public abstract class AbstractPet implements Pet{
    public static final String DOG = "dog";
    public static final String CAT = "cat";
    private final PetID id;
    private final String petType;
    private boolean adopted;
    private final String careInstructions;

    /**
     * AbstractPet(String, String) - package-private constructor to create a pet Object
     * @param petType - String representing the petType
     * @param careInstructions - String representing how a pet should be cared for.
     */
    AbstractPet (String petType, String careInstructions){
        this.petType = petType;
        this.careInstructions = careInstructions;
        this.adopted = false;
        this.id= PetID.getInstance();
    }

    /**
     * AbstractPet(Pet) - a constructor for copying a pet object
     * @param pet - the object to be copied
     */
    AbstractPet (Pet pet){ //copy constructor
        this.id = pet.getPetID();//PetId class is immutable, so do not need to create new PetId instance
        this.petType = pet.getPetType();
        this.careInstructions = getCareInstructions();
        this.adopted = getAdopted();
    }

    /**
     * getInstance(String) - creates and returns pet object (acts as a factory).
     * @param petType - a string (lowercase) representing the requested class type (e.g. dog or cat).
     * @return instance of a class that implements pet interface, or null if the Pet object has a null PetID (occurs if no more PetIDs can be created), if there is no class of the petType requested, or if the parameter is null or blank.
     */
    public static Pet getInstance(String petType) {
        if(petType == null || petType.isBlank()){
            return null;
        }else{
            petType = petType.toLowerCase().trim();
        }

        Pet pet;
        if(petType.equals(DOG)){
            pet = new Dog();
        } else if(petType.equals(CAT)){
            pet = new Cat();
        } else {
            return null;
        }
        if(pet.getPetID()== null){
            return null;
        }else{
            return pet;
        }
    }

    /**
     * copy(Pet) - creates a new pet object with the same values the given pet object by invoking appropriate copy constructors
     * @param pet the Pet object to be copied
     * @return Pet object equal to parameter.
     */
    public static Pet copy(Pet pet){
        if(pet.getPetType().equals(DOG)){
            Dog dog = (Dog) pet; // can downcast to dog class as checked the stored petTypes matches
            return new Dog(dog);
        } else if(pet.getPetType().equals(CAT)){
            Cat cat = (Cat) pet; // can downcast to cat class as checked the stored petType matches
            return new Cat(cat);
        } else {
            return null;
        }
    }

    /**
     * getPetID() - retrieves Pet objects PetID
     * @return PetId object stored by id field.
     */
    @Override
    public PetID getPetID() {
        return id; // do not need to create a copy of the object (for defensive copying) as object is immutable.
    }

    /**
     * getPetType() - retrieves Pet objects petType
     * @return string representing petType e.g. "dog"
     */
    @Override
    public String getPetType() {
        return petType;
    }

    /**
     * getAdopted() - retrieves whether Pet object has been adopted (true or false)
     * @return boolean representing if pet has been adopted.
     */
    @Override
    public boolean getAdopted() {
        return adopted;
    }

    /**
     * setAdopted(boolean) - sets field that represents whether pet has been adopted
     * @param adopted true if pet is adopted.
     */
    @Override
    public void setAdopted(boolean adopted){
        this.adopted = adopted;
    }

    /**
     * getCareInstructions() - retrieves String information on how a pet object should be cared for
     * @return String containing pet care information.
     */
    @Override
    public String getCareInstructions() {
        return careInstructions;
    }

    /**
     * toString() - converts object fields to a single string representation
     * @return String containing: PetID petID, String petType, String careInstructions, boolean adopted
     */
    @Override
    public String toString() {
        return (id.toString() +","+ petType+","+ careInstructions + ","+adopted);
    }

    /**
     * equals(Pet) - compares this Pet object to another by their respective PetIDs.
     * @param otherPet pet object to be compared to
     * @return true if pets have the same id, false otherwise.
     */
    public boolean equals(Pet otherPet){
        return this.id.equals(otherPet.getPetID());
    }

    /**
     * hashCode() - returns a single hashcode value representing Pet ID.
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        int initialValue = 37;
        int multiplier = 11;
        int hc = initialValue;

        hc = multiplier*hc + this.id.hashCode();
        return hc;
    }
}
