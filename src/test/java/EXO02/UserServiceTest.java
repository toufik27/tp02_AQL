package EXO02;
import org.example.EXO02.ServiceException;
import org.example.EXO02.UserService;
import org.example.EXO02.Utilisateur;
import org.example.EXO02.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Test
    public void testCreateUtser() throws ServiceException {
        Utilisateur user = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        // TODO : Configuration du comportement du mock, utiliser la
        when(utilisateurApiMock.creerUtilisateur(user)).thenReturn(1);
        // TODO : Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);
        // TODO : Appel de la méthode à tester
        userService.creerUtilisateur(user);
        // TODO : Vérification de l'appel à l'API
        verify(utilisateurApiMock, times(1)).creerUtilisateur(user);
    }
}