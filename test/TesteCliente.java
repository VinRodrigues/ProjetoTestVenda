import Controller.ControllerCliente;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import java.util.Calendar;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 * @author vinicius
 */
public class TesteCliente {
    private ClientePF pessoa;
    private ClientePJ empresa;
    private ControllerCliente cliente;

    @Before
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, Calendar.MAY, 28);

        pessoa = new ClientePF(1, calendar.getTime(), "ClienteP", "12345678912", 123456789);
        empresa = new ClientePJ(2, calendar.getTime(), "ClienteE", "1223456300189", 987654321);
        cliente = new ControllerCliente();

        // Insere o cliente para garantir que ele existirá nos outros testes
        cliente.persistirBanco(pessoa, true, false, false);
        cliente.persistirBanco(empresa, true, false, false);
    }

    @Test
    public void testPersistirBancoInsercaoPF() {

        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se os dados do cliente são os esperados
        assertEquals(pessoa.getNome(), result.getNome());
        assertEquals(pessoa.getCPF(), ((ClientePF) result).getCPF());
        assertEquals(pessoa.getIdentidade(), ((ClientePF) result).getIdentidade());
    }

    @Test
    public void testPersistirBancoInsercaoPJ() {
        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se os dados do cliente são os esperados
        assertEquals(empresa.getNome(), result.getNome());
        assertEquals(empresa.getCnpj(), ((ClientePJ) result).getCnpj());
        assertEquals(empresa.getInscricaoEstadual(), ((ClientePJ) result).getInscricaoEstadual());
    }

    @Test
    public void testPersistirBancoAtualizacaoPF() {
        pessoa.setNome("Pessoa Teste Atualizada");
        cliente.persistirBanco(pessoa, false, true, false);

        // Buscar o cliente do banco para verificar se foi atualizado corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se os dados do cliente foram atualizados corretamente
        assertEquals(pessoa.getNome(), result.getNome());
    }

    @Test
    public void testPersistirBancoAtualizacaoPJ() {
        empresa.setNome("Empresa Teste Atualizada");
        cliente.persistirBanco(empresa, false, true, false);

        // Buscar o cliente do banco para verificar se foi atualizado corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se os dados do cliente foram atualizados corretamente
        assertEquals(empresa.getNome(), result.getNome());
    }

    @Test
    public void testPersistirBancoExclusaoPF() {
        cliente.persistirBanco(pessoa, false, false, true);

        // Buscar o cliente do banco para verificar se foi excluído corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se o cliente não existe mais
        assertNull(result);
    }

    @Test
    public void testPersistirBancoExclusaoPJ() {
        cliente.persistirBanco(empresa, false, false, true);

        // Buscar o cliente do banco para verificar se foi excluído corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se o cliente não existe mais
        assertNull(result);
    }

    @Test
    public void testObterClientePorIdExistente() {
        // Buscar um cliente que existe no banco de dados
        Cliente result = cliente.getCliente(1);

        // Verificar se o cliente foi retornado corretamente
        assertNotNull(result);
        assertEquals("Pessoa Teste", result.getNome());
    }

    @Test
    public void testObterClientePorIdInexistente() {
        // Buscar um cliente que não existe no banco de dados
        Cliente result = cliente.getCliente(999);

        // Verificar se o resultado é nulo
        assertNull(result);
    }
}