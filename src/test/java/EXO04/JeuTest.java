package EXO04;

import org.example.EXO04.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JeuTest {
    @Test
    public void testCloseGameShouldReturnFalse() {
        Banque banque = new BanqueImplementation();
        Jeu jeu = new Jeu(banque);
        jeu.fermer();
        assertFalse(jeu.estOuvert());
    }

    @Test
    public void testPlayerInsolventShouldReturnFalse() {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        when(banque.est_solvable()).thenReturn(true);
        when(joueur.est_solvable()).thenReturn(false);

        Jeu jeu = new Jeu(banque);

        try {
            jeu.jouer(joueur, de1, de2);
            fail("Exception JeuFermeException attendue.");
        } catch (JeuFermeException e) {
            assertEquals("La banque ou le joueur est insolvable.", e.getMessage());
            assertFalse(jeu.estOuvert());
        } catch (DebitImpossibleException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBankInsolventAfterWinShouldThrowException()  {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);


        when(joueur.est_solvable()).thenReturn(true);
        when(banque.est_solvable()).thenReturn(true, false);

        Jeu jeu = new Jeu(banque);

        when(de1.lancer()).thenReturn(1);
        when(de2.lancer()).thenReturn(6);

        assertTrue(jeu.estOuvert());

        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));

        assertFalse(jeu.estOuvert());
    }

    @Test
    public void testPlayerBetSumDiceNotSevenShouldReturnFalse() {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        when(banque.est_solvable()).thenReturn(true);
        when(joueur.est_solvable()).thenReturn(true);
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(4);

        Jeu jeu = new Jeu(banque);

        try {
            jeu.jouer(joueur, de1, de2);
            verify(joueur, times(1)).debiter(10);
            verify(joueur, never()).crediter(anyInt());
            assertFalse(jeu.estOuvert());
        } catch (JeuFermeException | DebitImpossibleException e) {
            fail("Aucune exception n'était attendue.");
        }
    }

    @Test
    public void testPlayerBetSumDiceEqualSevenShouldReturnTrue() {
        Banque banque = mock(Banque.class);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        when(banque.est_solvable()).thenReturn(true);
        when(joueur.est_solvable()).thenReturn(true);
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);

        Jeu jeu = new Jeu(banque);

        try {
            jeu.jouer(joueur, de1, de2);
            verify(joueur, times(1)).debiter(10);
            verify(joueur, times(1)).crediter(20);
            assertTrue(jeu.estOuvert());
        } catch (JeuFermeException | DebitImpossibleException e) {
            fail("Aucune exception n'était attendue.");
        }
    }

    @Test
    public void testMinimumFundAfterWinningBet() throws JeuFermeException, DebitImpossibleException {
        BanqueImplementationQ7 banque = new BanqueImplementationQ7(1000, 500);
        Joueur joueur = mock(Joueur.class);
        De de1 = mock(De.class);
        De de2 = mock(De.class);

        when(joueur.est_solvable()).thenReturn(true);

        when(de1.lancer()).thenReturn(1);
        when(de2.lancer()).thenReturn(6);

        Jeu jeu = new Jeu(banque);
        jeu.jouer(joueur, de1, de2);

        assertTrue(banque.est_solvable());
        assertEquals(1000, banque.fond);

        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(5);

        jeu.jouer(joueur, de1, de2);

        assertFalse(banque.est_solvable());
        assertEquals(500, banque.fond);
    }

}
