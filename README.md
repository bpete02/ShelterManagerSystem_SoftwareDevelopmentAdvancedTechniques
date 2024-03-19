This tasks was completed as an introduction to 'advanced software development techniques' including subjects such as defensive copying, casting, and late binding. The aim was to establish interfaces and classes that could be used in the development of an application, but not to develop an interface.

System Overview:
  The shelter management system facilitates the temporary housing of stray or unwanted pets, preparing them for eventual adoption. The system must manage two types of pets: cats and dogs. It offers services for adding new pets to the shelter and facilitating pet adoption to customers. Additionally, it maintains records of both pets within the shelter and customers who adopt them.

Tasks/Subtasks:
Part 1: Adding a new pet to the shelter:
Task 1.1:
Define a class hierarchy for pets, creating appropriate classes to represent cats and dogs.
Implement methods to manage pet IDs, types, adoption status, care instructions, and dog training status.

Task 1.2:
Create a class to manage unique pet IDs, including components and string representation.
Ensure the uniqueness of pet IDs.

Task 1.3:
Implement methods in the ShelterManager class for Part 1.
Manage a data structure for all pets in the shelter.
Methods include adding pets, updating pet records (including training status), and counting available pets of each type.

Part 2: Giving an existing pet to a customer for adoption:
Task 2.1:
Develop classes for managing customer records and customer numbers.
CustomerRecord includes customer information, a unique customer number, and garden ownership status.
CustomerNumber is composed of customer initials, serial number, and issue date.
Ensure uniqueness of customer numbers.

Task 2.2:
Implement methods in ShelterManager for Part 2.
Manage data structures for existing customer records and customer-pet adoptions.
Methods include adding customer records and facilitating pet adoptions.
Ensure adherence to adoption rules for customer eligibility and pet availability.

Adoption Rules Details:
Customers cannot adopt more than three pets of any type.
Age and garden ownership requirements for adopting dogs vary based on the dog's training status.
For trained dogs, customers must be at least 18 years old and have a garden.
For untrained dogs, customers must be at least 21 years old and have a garden.
To adopt a cat, customers must be at least 18 years old.
Adopted pets' records remain in the shelter, but their status changes from "not adopted" to "adopted."
The method adoptedPetsByCustomer returns an unmodifiable collection of all pets adopted by a specific customer.
