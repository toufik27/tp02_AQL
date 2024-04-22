package org.example.EXO04;

public interface Banque {
    void crediter(int somme);
    boolean est_solvable();
    void debiter(int somme);
}
