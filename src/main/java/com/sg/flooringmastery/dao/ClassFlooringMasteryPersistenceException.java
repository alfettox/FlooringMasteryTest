package com.sg.flooringmastery.dao;

/**
 * GROUP 2
 * Wiley Edge
 **/
public class ClassFlooringMasteryPersistenceException extends Exception {

    public ClassFlooringMasteryPersistenceException(String message) {
        super(message);     //CALL THE CONSTRUCTOR CLASS OF THE PARENT CLASS, WE WANT THIS CLASS TO ACT AS THE PARENT
    }

    public ClassFlooringMasteryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
