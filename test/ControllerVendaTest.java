import Controller.*;
import Model.*;
import Model.DAO.DAOConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ian21
 */
public class ControllerVendaTest {

    private ControllerVenda controllerVenda;
    private Venda venda;
    private ControllerProduto produtoController;
    private ControllerCliente clienteController;
    private Date dataNascimento;
    private ClientePF clientePF;

    @Before
    public void setUp() throws SQLException {
        controllerVenda = new ControllerVenda();
        produtoController = new ControllerProduto();
        clienteController = new ControllerCliente();

        // Limpar banco antes de cada teste
        limparBD();

        // Criando informações necessárias para funcionamento do teste
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JUNE, 21);
        dataNascimento = calendar.getTime();
        clientePF = new ClientePF(9, dataNascimento, "Pessoa Teste", "22119051052", 109283745);
        clienteController.persistirBanco(clientePF, true, false, false);

        Produto produto = new Produto(5, "Produto Teste", (float) 3.0);
        produtoController.persistirBanco(produto, true, false, false);

        VendaItem vendaItem = new VendaItem(7, 1, (float) 3.0);
        vendaItem.setProduto(produto);

        venda = new Venda(5, calendar.getTime(), clientePF, (float) 2.0, (float) 10.0);
        venda.adicionaItem(vendaItem);
    }

    @Test
    public void TesteParaInserirBancoTest() {
        // Inserir venda
        controllerVenda.persistirBanco(venda);

        // Verificar venda
        Venda result = getVendaById(controllerVenda.getVendas(), venda.getIdVenda());

        // Verificar informações
        assertNotNull("Venda não encontrada no banco de dados.", result);
        assertEquals(venda.getCliente().getNome(), result.getCliente().getNome());
        assertEquals(venda.getValorPago(), result.getValorPago(), 0.001);
        assertEquals(venda.getTotalVendaLiquida(), result.getTotalVendaLiquida(), 0.001);
        System.out.println("TesteParaInserirBancoTest: OK");
    }

    @Test
    public void TesteParaSimularBancoVazioTest() throws SQLException {
        // Limpar banco antes do teste
        limparBD();
        
        // Simular lista vazia
        ArrayList<Venda> vendas = controllerVenda.getVendas();
        assertTrue("A lista de vendas deveria estar vazia.", vendas.isEmpty());
        System.out.println("TesteParaSimularBancoVazioTest: OK");
    }

    @Test
    public void TesteParaPegarListaPopuladaTeste() {
        // Inserir vendas para teste
        controllerVenda.persistirBanco(venda);
        controllerVenda.persistirBanco(new Venda(10, dataNascimento, clientePF, 10.0f, 20.0f));

        ArrayList<Venda> vendas = controllerVenda.getVendas();
        // Lista não vazia
        assertFalse("A lista de vendas não deveria estar vazia.", vendas.isEmpty());
        // Duas vendas na lista
        assertEquals("A lista de vendas deveria ter 2 itens.", 2, vendas.size());
        System.out.println("TesteParaPegarListaPopuladaTeste: OK");
    }

    // Método auxiliar getVendaById
    public static Venda getVendaById(ArrayList<Venda> listaVenda, int id) {
        for (Venda venda : listaVenda) {
            if (venda.getIdVenda() == id) {
                return venda;
            }
        }
        return null;
    }

    // Método auxiliar para limpar o banco de dados
    private void limparBD() throws SQLException {
        DAOConexaoDB conexaoDB = new DAOConexaoDB();
        Connection connection = null;
        PreparedStatement disableFK = null;
        PreparedStatement pst = null;

        try {
            connection = conexaoDB.getConexao();

            // Desativar as verificações de chave estrangeira
            disableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.executeUpdate();

            String[] tabelas = {"tb_venda_item", "tb_venda", "tb_produto", "tb_cliente"};
            for (String tabela : tabelas) {
                pst = connection.prepareStatement("DELETE FROM " + tabela);
                pst.executeUpdate();
            }

            // Reativar as verificações de chave estrangeira
            PreparedStatement enableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.executeUpdate();
        } finally {
            // Fechar recursos em um bloco finally para garantir que sejam fechados mesmo se ocorrer uma exceção
            if (disableFK != null) {
                disableFK.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
            conexaoDB.close();
        }
    }
}
