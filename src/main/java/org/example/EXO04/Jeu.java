package org.example.EXO04;
import org.example.*;

public class Jeu {
    private Banque laBanque;
    private boolean estOuvert;

    public Jeu(Banque laBanque) {
        this.laBanque = laBanque;
        this.estOuvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException, DebitImpossibleException {
        if (!estOuvert) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        if (!laBanque.est_solvable()) {
            fermer();
            throw new JeuFermeException("La banque n'est plus solvable.");
        }

        int sommeDes = de1.lancer() + de2.lancer();

        if (sommeDes == 7) {
            int mise = joueur.mise();
            laBanque.debiter(mise);
            joueur.crediter(2 * mise);
            if (!laBanque.est_solvable()) {
                fermer();
            }
        } else {
            joueur.debiter(joueur.mise());
            fermer();
        }
    }

    public void fermer() {
        this.estOuvert = false;
    }

    public boolean estOuvert() {
        return estOuvert;
    }
}