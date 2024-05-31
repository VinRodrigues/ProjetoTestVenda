/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.ControllerLogin;
import Model.DAO.DAOLogin;
import Model.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
/**
 *
 * @author vinicius
 */
public class TesteLogin {

    @InjectMocks
    private ControllerLogin controllerLogin;

    @Mock
    private DAOLogin mockDAOLogin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidaLogin_ValidCredentials_ReturnsLogin() {
        // Arrange
        Login expectedLogin = new Login("user1", "password1", "User One", "ADMIN");
        when(mockDAOLogin.validaLogin("user1", "password1", "ADMIN")).thenReturn(expectedLogin);

        // Act
        Login result = controllerLogin.validaLogin("user1", "password1", "ADMIN");

        // Assert
        assertEquals(expectedLogin, result);
    }

    @Test
    public void testValidaLogin_InvalidCredentials_ReturnsNull() {
        // Arrange
        when(mockDAOLogin.validaLogin("user1", "wrongpassword", "ADMIN")).thenReturn(null);

        // Act
        Login result = controllerLogin.validaLogin("user1", "wrongpassword", "ADMIN");

        // Assert
        assertNull(result);
    }

    @Test
    public void testValidaLogin_EmptyCredentials_ReturnsNull() {
        // Arrange
        when(mockDAOLogin.validaLogin("", "", "")).thenReturn(null);

        // Act
        Login result = controllerLogin.validaLogin("", "", "");

        // Assert
        assertNull(result);
    }

    @Test
    public void testGetPrivilegios_ReturnsListOfPrivileges() {
        // Arrange
        ArrayList<String> expectedPrivileges = new ArrayList<>(Arrays.asList("ADMIN", "USER"));
        when(mockDAOLogin.getPrivilegios()).thenReturn(expectedPrivileges);

        // Act
        ArrayList<String> result = controllerLogin.getPrivilegios();

        // Assert
        assertEquals(expectedPrivileges, result);
    }

    @Test
    public void testGetPrivilegios_NoPrivileges_ReturnsEmptyList() {
        // Arrange
        when(mockDAOLogin.getPrivilegios()).thenReturn(new ArrayList<>());

        // Act
        ArrayList<String> result = controllerLogin.getPrivilegios();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    public void testValidaLogin_ValidCredentialsButDifferentPriv_ReturnsNull() {
        // Arrange
        when(mockDAOLogin.validaLogin("user1", "password1", "USER")).thenReturn(null);

        // Act
        Login result = controllerLogin.validaLogin("user1", "password1", "USER");

        // Assert
        assertNull(result);
    }
}
