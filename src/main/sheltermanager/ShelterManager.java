package main.sheltermanager;

import main.customer.CustomerNumber;
import main.customer.CustomerRecord;
import main.pet.*;

import java.util.*;

/**
 * ShelterManager - class that stores and alters lists of Pets, Customers, and which pets have been adopted by which customers
 *
 * @author Billy Peters
 * 21/02/2024
 */
public class ShelterManager {

	private static final Map<PetID, Pet> PETS = new HashMap<PetID,Pet>(); // a collection to store all pets
	private static final Map<CustomerNumber, CustomerRecord> CUSTOMERS= new HashMap<CustomerNumber,CustomerRecord>(); // a collection to store all customers
	private static final Map<PetID,CustomerNumber> ADOPTEDPETS = new HashMap<PetID,CustomerNumber>(); // a collection to match adopted pets to their adopting customers (with pets as a key so multiple pets can be assigned to same customer).
	//you can add attributes and additional methods if needed. 
	//you can throw an exception if needed

	
	//Task 1.3 methods

	/**
	 * ShelterManager - Empty Constructor as all fields are static, final, and already initialised.
	 */
	public ShelterManager(){
	}

	/**
	 * addPet(String) - adds a Pet object to the stores list of (unadopted) pets.
	 * @param petType - String of pet type to create (e.g. dog)
	 * @return a copy of the new Pet object, or null if the pet could not be created, added, or if the parameters were null/empty
	 */
	public Pet addPet(String petType){
		if(petType == null || petType.isBlank()){
			return null;
		}else{
			petType = petType.toLowerCase().trim();
		}


		Pet newPet = AbstractPet.getInstance(petType);
		if(newPet == null){
			return null;
		}else{
			PETS.put(newPet.getPetID(), newPet);
			return AbstractPet.copy(newPet); //returns copy of pet object as pet may be mutable.
		}
	}


	/**
	 * updatePetRecord(PetID, Boolean) - updates a Pet object to store whether it is trained, if it can be trained
	 * @param petID Id of the pet to be updated
	 * @param trained true if pet is trained, false otherwise
	 * @return true if pet is updated, false if pet cannot be trained, or null if pet does not exist or the parameters are null.
	 */
	public Boolean updatePetRecord (PetID petID, Boolean trained) {
		if(petID == null || trained == null){
			return null;
		}

		if (PETS.containsKey(petID)) {
			Pet alterPet = PETS.get(petID);
			if (alterPet instanceof Trainable){
				Trainable trainPet = (Trainable) alterPet; // created Trainable interface to cast to instead of casting to dog, in case a new pet class is added that is also trainable.
				trainPet.setTrained(trained);
				return true;
			} else{
				return false;
			}
		} else{
			return null;
		}
	}

	/**
	 * noOfAvailablePets(String) - finds the number of unadopted pets that are of a particular type
	 * @param petType - type of pet wanted (e.g. dog)
	 * @return int number of availbale pets, or 0 if parameters are null,empty, or if the pet type does not exist in shelter's list of pets.
	 */
	public int noOfAvailablePets(String petType) {
		if(petType == null||petType.isBlank()){
			return 0;
		}else{
			petType = petType.toLowerCase().trim();
		}

		int numPets = 0;
		for(Map.Entry<PetID,Pet> entry : PETS.entrySet()) {// Iterating through HashMaps example found here: https://sentry.io/answers/iterate-hashmap-java/ 	Original Author: Lewis D	Modifying Author: 230554812
			Pet currentPet = entry.getValue();
			if(petType.equals(currentPet.getPetType())&&!(currentPet.getAdopted())){
				numPets += 1;
			}
		}
		return numPets;
	}


	//Task 2.2 methods

	/**
	 * addCustomerRecord(String, String, Date, Boolean) - adds a new CustomerRecord object to shelter's list of customers.
	 * @param firstName customer's first name
	 * @param lastName customer's last name
	 * @param dob customer's date of birth
	 * @param hasGarden true if customer has a garden, false otherwise.
	 * @return CustomerObject added to the list or null if parameters are null or empty.
	 */
	public CustomerRecord addCustomerRecord(String firstName, String lastName, Date dob, Boolean hasGarden) {
		if(firstName == null || lastName == null || dob == null || hasGarden == null || firstName.isBlank() || lastName.isBlank()){
			return null;
		}else{
			firstName = firstName.trim();
			firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();

			lastName = lastName.trim();
			lastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1).toLowerCase();
		}

		for(Map.Entry<CustomerNumber,CustomerRecord> entry:CUSTOMERS.entrySet()){
			CustomerRecord currentCustomer = entry.getValue();
			if(currentCustomer.getName().getFirstName().equals(firstName)&&currentCustomer.getName().getLastName().equals(lastName)&&currentCustomer.getDob().equals(dob)){
				return null;
			}
		}


		CustomerRecord newCustomer = CustomerRecord.getInstance(firstName, lastName, dob, hasGarden);
		if(!(newCustomer ==null)) {
			CUSTOMERS.put(newCustomer.getCustomerNumber(), newCustomer); //can return newCustomer object without copying as customer is immutable.
			return newCustomer;
		} else{
			return null;
		}
	}

	/**
	 * adoptPet(CustomerRecord, String) - Allocates a random pet (of petType) to the CustomerRecord, updates the list of adopted pets, as long as the customer meets adoption requirements.
	 * @param customerRecord Customer that wants to adopt a pet
	 * @param petType Type of pet that the customer wants to adopt
	 * @return True if a pet is adopted, false if a pet cannot be adopted, null if parameters are null or blank.
	 */
	public Boolean adoptPet(CustomerRecord customerRecord, String petType) {
		if(customerRecord == null || petType == null || petType.isBlank()){
			return null;
		}else{
			petType = petType.toLowerCase().trim();
		}

		if(customerRecord.calcAge()<18){ //customer cannot adopt pet if under 18
			System.out.println("Customer cannot adopt pets if under the age of 18");
			return false;
		}

		int petsAdoptedByCustomer = 0;

		for(Map.Entry<PetID,CustomerNumber> entry:ADOPTEDPETS.entrySet()){
			CustomerNumber currentCustomer = entry.getValue();
			if(currentCustomer.equals(customerRecord.getCustomerNumber())){
				petsAdoptedByCustomer += 1;
			}
		}

		if(petsAdoptedByCustomer > 2){ // customer cannot adopt pet they have already adopted 3.
			System.out.println("Customer has already adopted the maximum number of pets (3)");
			return false;
		}

		List<Pet> availablePetsOfType = new ArrayList<Pet>();
		for(Map.Entry<PetID,Pet> entry: PETS.entrySet()){
			Pet currentPet = entry.getValue();

			if(currentPet.getPetType().equals(petType) && !currentPet.getAdopted()){
				availablePetsOfType.add(currentPet);
			}
		}

		if(availablePetsOfType.isEmpty()){
			System.out.println("There are no unadopted "+ petType+"s available");
			return false;
		} else if((availablePetsOfType.get(0) instanceof NeedsGarden) && !customerRecord.getHasGarden()){ // customer must have a garden if their requested pet type needs a garden
			System.out.println("Customer cannot adopt a "+petType+" without a garden");
			return false;
		}

		List<Pet> availablePetsAfterTrainable;
		if(customerRecord.calcAge()<21 && availablePetsOfType.get(0) instanceof Trainable){ // customers under 21 cannot adopt untrained pets
			availablePetsAfterTrainable = new ArrayList<>();
			for(Pet pet:availablePetsOfType){
				Trainable trainablePet = (Trainable) pet;
				if(trainablePet.getTrained()){
					availablePetsAfterTrainable.add(pet);
				}
			}
			if(availablePetsAfterTrainable.isEmpty()) {
				System.out.println("Customer's under 21 cannot adopt an untrained " + petType + " and no trained " + petType + "s are available");
				return false;
			}

		} else{
			availablePetsAfterTrainable = new ArrayList<>(availablePetsOfType);
		}

		int randomIndex = new Random().nextInt(availablePetsAfterTrainable.size());
		Pet adoptedPet = availablePetsAfterTrainable.get(randomIndex);
		adoptedPet.setAdopted(true);
		ADOPTEDPETS.put(adoptedPet.getPetID(),customerRecord.getCustomerNumber());

		if(adoptedPet instanceof Trainable){
			boolean isTrained = ((Trainable) adoptedPet).getTrained();
			if(isTrained){
				System.out.println("Adopted: trained "+petType);
				return true;
			} else if(!isTrained){
				System.out.println("Adopted: untrained "+petType);
				return true;
			}
		}

		System.out.println("Adopted: "+petType);
		return true;



    }

	/**
	 * adoptedPetsByCustomer(CustomerNumber) - retrieves which pets are adopted by a customer
	 * @param customerNumber - customer to find which pets belong to
	 * @return Collection of Pet objects adopted by the customer, or null if parameter is null or the shelter does not contain a customer with the given CustomerNumber.
	 */
	public Collection<Pet> adoptedPetsByCustomer (CustomerNumber customerNumber){
		if(customerNumber == null || !CUSTOMERS.containsKey(customerNumber)){
			return null;
		}
		Set<Pet> petsAdoptedByCustomer = new HashSet<Pet>();
		for(Map.Entry<PetID,CustomerNumber> entry:ADOPTEDPETS.entrySet()) {
			PetID currentPet = entry.getKey();
			CustomerNumber currentCustomerId = entry.getValue();
			if (currentCustomerId.equals(customerNumber)){
				Pet adoptedPet = PETS.get(currentPet);
				petsAdoptedByCustomer.add(AbstractPet.copy(adoptedPet));
			}
		}
		petsAdoptedByCustomer = Collections.unmodifiableSet(petsAdoptedByCustomer);
		return petsAdoptedByCustomer;
	}
}
