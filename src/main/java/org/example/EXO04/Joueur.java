package org.example.EXO04;


public interface Joueur {
    int mise();
    void debiter(int somme) throws DebitImpossibleException;
    void crediter(int somme);

    Object est_solvable();
}