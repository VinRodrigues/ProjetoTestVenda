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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author vinicius
 */
public class TesteCliente {

    @InjectMocks
    private ControllerCliente controller;

    @Mock
    private DAOCliente mockDAOCliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testObterClientes() {
        // Arrange
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345));
        clientes.add(new ClientePJ(2, new Date(), "Cliente 2", "12345678901234", 54321));
        when(mockDAOCliente.getAll()).thenReturn(clientes);
        
        // Act
        ArrayList<Cliente> resultado = controller.getClientes();
        
        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    public void testObterClientePorId() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        when(mockDAOCliente.getCliente(1)).thenReturn(cliente);

        // Act
        Cliente resultado = controller.getCliente(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(cliente, resultado);
    }

    @Test
    public void testObterClientePorIdNaoEncontrado() {
        // Arrange
        when(mockDAOCliente.getCliente(1)).thenReturn(null);

        // Act
        Cliente resultado = controller.getCliente(1);

        // Assert
        assertNull(resultado);
    }

    @Test
    public void testPersistirClienteNoBancoInserir() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, true, false, false);
        
        // Assert
        verify(mockDAOCliente, times(1)).insert(cliente);
    }

    @Test
    public void testPersistirClienteNoBancoEditar() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, false, true, false);
        
        // Assert
        verify(mockDAOCliente, times(1)).update(cliente);
    }

    @Test
    public void testPersistirClienteNoBancoDeletar() {
        // Arrange
        ClientePF cliente = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345);
        
        // Act
        controller.persistirBanco(cliente, false, false, true);
        
        // Assert
        verify(mockDAOCliente, times(1)).delete(cliente);
    }

    @Test
    public void testToStringClientes() {
        // Arrange
        ArrayList<Cliente> clientes = new ArrayList<>();
        Date data = new Date();
        clientes.add(new ClientePF(1, data, "Cliente 1", "12345678901", 12345));
        clientes.add(new ClientePJ(2, data, "Cliente 2", "12345678901234", 54321));
        when(mockDAOCliente.getAll()).thenReturn(clientes);

        String esperado = "Cliente{id=1, data=" + data + ", nome='Cliente 1', cpf='12345678901', identidade=12345}\n" +
                          "Cliente{id=2, data=" + data + ", nome='Cliente 2', cnpj='12345678901234', inscricaoEstadual=54321}\n";

        // Act
        String resultado = controller.toString();

        // Assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void testPersistirClienteNoBancoInserirNulo() {
        // Arrange
        ClientePF cliente = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> controller.persistirBanco(cliente, true, false, false));
    }

    @Test
    public void testObterClientesListaVazia() {
        // Arrange
        when(mockDAOCliente.getAll()).thenReturn(new ArrayList<>());

        // Act
        ArrayList<Cliente> resultado = controller.getClientes();

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }

    @Test
    public void testObterClientesUnicoCliente() {
        // Arrange
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new ClientePF(1, new Date(), "Cliente 1", "12345678901", 12345));
        when(mockDAOCliente.getAll()).thenReturn(clientes);

        // Act
        ArrayList<Cliente> resultado = controller.getClientes();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Cliente 1", resultado.get(0).getNome());
    }
}