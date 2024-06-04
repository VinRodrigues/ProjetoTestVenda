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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author ian21
 */

public class ControllerVendaTest {

    private ControllerVenda controllerVenda;
    private Venda venda;
    private ControllerProduto produtoController;
    private ControllerCliente clienteController;

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
        Date dataNascimento = calendar.getTime();
        ClientePF clientePF = new ClientePF(9, dataNascimento, "Pessoa Teste", "22119051052", 109283745);
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
        
        // Verificar informacoes 
        assertEquals(venda.getCliente().getNome(), result.getCliente().getNome());
        assertEquals(venda.getValorPago(), result.getValorPago(), 0.001);
        assertEquals(venda.getTotalVendaLiquida(), result.getTotalVendaLiquida(), 0.001);
        System.out.println("TesteParaInserirBancoTest: OK");
    }

    @Test
    public void TesteParaSimularBancoVazioTest() {
        
        // Simular lista vazia
        ArrayList<Venda> vendas = controllerVenda.getVendas();
        assertTrue(vendas.isEmpty());
        System.out.println("TesteParaSimularBancoVazioTest: OK");
    }

    @Test
    public void TesteParaPegarListaPopuladaTeste() {
        
        // Inserir vendas para teste
        controllerVenda.persistirBanco(venda);
        controllerVenda.persistirBanco(new Venda(10, this.dataNascimento, this.clientePF, 10.0f, 20.0f));

        ArrayList<Venda> vendas = controllerVenda.getVendas();
        // Lista não vazia
        assertFalse(vendas.isEmpty()); 
        // Duas vendas na lista
        assertEquals(2, vendas.size()); 
        System.out.println("TesteParaPegarListaPopuladaTeste: OK");
    }
    
    // getVendaByID para auxiliar testes que necessitam
        public static Venda getVendaById(ArrayList<Venda> listaVenda, int id) {
        for (Venda venda : listaVenda) {
            if (venda.getIdVenda() == id) {
                return venda;
            }
        }
        return null; 
    }
    
    private void limparBD() throws SQLException {
        
        DAOConexaoDB conexaoDB = new DAOConexaoDB();
        Connection connection = conexaoDB.getConexao();

        String[] tabelas = {"tb_venda_item", "tb_venda", "tb_produto", "tb_cliente"};
        try {
            // Desativar as verificações de chave estrangeira
            PreparedStatement disableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.executeUpdate();
            disableFK.close();

            for (String tabela : tabelas) {
                PreparedStatement pst = connection.prepareStatement("DELETE FROM " + tabela);
                pst.executeUpdate();
                pst.close();
            }

            // Reativar as verificações de chave estrangeira
            PreparedStatement enableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.executeUpdate();
            enableFK.close();
        } finally {
            // Fechar conexao geral
            connection.close();
            conexaoDB.close();
        }
    }
}
