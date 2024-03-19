package main.pet;

/**
 * Trainable - an interface representing a trainable pet
 *
 * @author Billy Peters
 * 21/02/2024
 */
public interface Trainable extends Pet {

    /**
     * returns a boolean representing whether the pet has been trained
     * @return true if pet is trained
     */
    boolean getTrained();

    /**
     * sets boolean field representing whether the pet has been trained
     * @param trained true if pet is trained
     */
    void setTrained(boolean trained);
}
