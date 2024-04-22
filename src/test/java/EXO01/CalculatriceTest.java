package EXO01;

import org.example.EXO01.Calculatrice;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {
    @Mock
    private Calculatrice calculatrice;
    @Test
    public void testAdditionner() {
        when(calculatrice.additionner(2, 3)).thenReturn(5);
        //TODO : Appel de la méthode à tester
        int result = calculatrice.additionner(2,3);
        //TODO : Vérification du résultat
        Assertions.assertEquals(5, result);
//TODO : Vérification que la méthode "additionner" a été appelée
// avec les arguments 2 et 3, utiliser la directive « verify »
        verify(calculatrice).additionner(2,3);
//TODO : Vérification qu'aucune autre méthode n'a été appelée sur

        verifyNoMoreInteractions(calculatrice);
// TODO : Vérification de l'état de l'objet après l'appel de la

        when(calculatrice.getState()).thenReturn(result);
        Assertions.assertEquals(result, calculatrice.getState());
    }
}