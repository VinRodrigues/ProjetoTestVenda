import Controller.ControllerCliente;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import Model.DAO.DAOCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ControllerClienteTest {

    @InjectMocks
    private ControllerCliente controller;

    @Mock
    private DAOCliente mockDAOCliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testGetClientes() {
        // Arrange
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345));
        clientes.add(new ClientePJ(2, new Date(), "Cliente 2", "12345678901234", 54321));
        when(mockDAOCliente.getAll()).thenReturn(clientes);
        
        // Act
        ArrayList<Cliente> result = controller.getClientes();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCliente() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        when(mockDAOCliente.getCliente(1)).thenReturn(cliente);

        // Act
        Cliente result = controller.getCliente(1);

        // Assert
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    public void testPersistirBancoInsert() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, true, false, false);
        
        // Assert
        verify(mockDAOCliente, times(1)).insert(cliente);
    }

    @Test
    public void testPersistirBancoEdit() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, false, true, false);
        
        // Assert
        verify(mockDAOCliente, times(1)).update(cliente);
    }

    @Test
    public void testPersistirBancoDelete() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, false, false, true);
        
        // Assert
        verify(mockDAOCliente, times(1)).delete(cliente);
    }

    @Test
    public void testToString() {
        // Arrange
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345));
        clientes.add(new ClientePJ(2, new Date(), "Cliente 2", "12345678901234", 54321));
        when(mockDAOCliente.getAll()).thenReturn(clientes);

        String expected = "Cliente{id=1, data=" + new Date() + ", nome='Cliente 1', cpf='12345678901', identidade=12345}\n" +
                          "Cliente{id=2, data=" + new Date() + ", nome='Cliente 2', cnpj='12345678901234', inscricaoEstadual=54321}\n";

        // Act
        String result = controller.toString();

        // Assert
        assertEquals(expected, result);
    }
}
