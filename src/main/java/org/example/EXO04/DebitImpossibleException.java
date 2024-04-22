package org.example.EXO04;

public class DebitImpossibleException extends Exception {
    public DebitImpossibleException() {
        super("Débit impossible.");
    }
}