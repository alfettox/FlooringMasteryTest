package com.sg.flooringmastery.service;

public class FlooringMasteryDuplicateOrderException extends Exception{
    public FlooringMasteryDuplicateOrderException(String message) {
        super(message);
    }

    public FlooringMasteryDuplicateOrderException(String message,
                                           Throwable cause) {
        super(message, cause);
    }

}
