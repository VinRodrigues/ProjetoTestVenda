
import Controller.ControllerCliente;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    String dataString = "28/12/1990"; 
    String dataString2 = "10/03/1910"; 

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    Date dataNascimento = null;
    Date dataFundacao = null;

    try {
        dataNascimento = formatter.parse(dataString); 
        dataFundacao = formatter.parse(dataString2);
    } catch (ParseException e) {
        e.printStackTrace();
        
    }

    pessoa = new ClientePF(1, dataNascimento, "Pessoa", "99999999999", 111111111);
    empresa = new ClientePJ(2, dataFundacao, "Empresa", "88888888888888", 000000000);
    cliente = new ControllerCliente();
    
    cliente.persistirBanco(pessoa, true, false, false);
    cliente.persistirBanco(empresa, true, false, false);

}
    
      @Test
    public void testeInsercaoClientePF() {
        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se os dados do cliente são os esperados
        assertNotNull("Cliente PF não encontrado no banco de dados.", result);
        assertEquals(pessoa.getNome(), result.getNome());
        assertEquals(pessoa.getCPF(), ((ClientePF) result).getCPF());
        assertEquals(pessoa.getIdentidade(), ((ClientePF) result).getIdentidade());
        System.out.println("testeInsercaoClientePF: OK");
    }

    @Test
    public void testeInsercaoClientePJ() {
        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se os dados do cliente são os esperados
        assertNotNull("Cliente PJ não encontrado no banco de dados.", result);
        assertEquals(empresa.getNome(), result.getNome());
        assertEquals(empresa.getCnpj(), ((ClientePJ) result).getCnpj());
        assertEquals(empresa.getInscricaoEstadual(), ((ClientePJ) result).getInscricaoEstadual());
        System.out.println("testeInsercaoClientePJ: OK");
    }

    @Test
    public void testeAtualizacaoClientePF() {
        pessoa.setNome("Pessoa Teste Atualizada");
        cliente.persistirBanco(pessoa, false, true, false);

        // Buscar o cliente do banco para verificar se foi atualizado corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se os dados do cliente foram atualizados corretamente
        assertNotNull("Cliente PF não encontrado no banco de dados.", result);
        assertEquals(pessoa.getNome(), result.getNome());
        System.out.println("testeAtualizacaoClientePF: OK");
    }

    @Test
    public void testeAtualizacaoClientePJ() {
        empresa.setNome("Empresa Teste Atualizada");
        cliente.persistirBanco(empresa, false, true, false);

        // Buscar o cliente do banco para verificar se foi atualizado corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se os dados do cliente foram atualizados corretamente
        assertNotNull("Cliente PJ não encontrado no banco de dados.", result);
        assertEquals(empresa.getNome(), result.getNome());
        System.out.println("testeAtualizacaoClientePJ: OK");
    }

    @Test
    public void testeExclusaoClientePF() {
        cliente.persistirBanco(pessoa, false, false, true);

        // Buscar o cliente do banco para verificar se foi excluído corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());

        // Verificar se o cliente não existe mais
        assertNull(result);
        System.out.println("testeExclusaoClientePF: OK");
    }

    @Test
    public void testeExclusaoClientePJ() {
        cliente.persistirBanco(empresa, false, false, true);

        // Buscar o cliente do banco para verificar se foi excluído corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());

        // Verificar se o cliente não existe mais
        assertNull(result);
        System.out.println("testeExclusaoClientePJ: OK");
    }


    @Test
    public void testeObterClientePorIdInexistente() {
        // Buscar um cliente que não existe no banco de dados
        Cliente result = cliente.getCliente(999);

        // Verificar se o resultado é nulo
        assertNull(result);
        System.out.println("testeObterClientePorIdInexistente: OK");
    }
}
