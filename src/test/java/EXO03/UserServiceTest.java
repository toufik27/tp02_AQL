package EXO03;

import org.example.EXO02.ServiceException;
import org.example.EXO02.UserService;
import org.example.EXO02.Utilisateur;
import org.example.EXO02.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Test
    public void testCreateUser() throws ServiceException {
        Utilisateur user = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(user);
        try {
            UserService userService = new UserService(utilisateurApiMock);
            userService.creerUtilisateur(user);
        } catch (ServiceException e) {
        }
        verify(utilisateurApiMock, never()).creerUtilisateur(user);
        doReturn(true).when(utilisateurApiMock).creerUtilisateur(user);
        int idUtilisateur = 123;
        doReturn(idUtilisateur).when(utilisateurApiMock).creerUtilisateur(user);
        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(user);
        user.setId(idUtilisateur);
        verify(utilisateurApiMock).creerUtilisateur(user);
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        doReturn(true).when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));
        userService.creerUtilisateur(user);
        Utilisateur utilisateurCapture = argumentCaptor.getValue();
        assertEquals(user.getNom(), utilisateurCapture.getNom());
        assertEquals(user.getPrenom(), utilisateurCapture.getPrenom());
        assertEquals(user.getEmail(), utilisateurCapture.getEmail());
    }
}