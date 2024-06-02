/*
import Controller.ControllerCliente;
import Controller.ControllerProduto;
import Controller.ControllerVenda;
import Model.ClienteCompra;
import Model.ItemVendido;
import Model.Venda;
import Model.ClientePF;
import Model.DAO.DAOConexaoDB;
import Model.DAO.DAOVenda;
import Model.Produto;
import Model.VendaItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerVendaTest {

         private ControllerVenda vendaController = new ControllerVenda();
    private Venda vendaTest;
    private ControllerProduto produtoController = new ControllerProduto();
    private ControllerCliente clienteController = new ControllerCliente();

    @Before
    public void prepararTeste() {
        limparBancoDados();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, Calendar.MAY, 30);

        ClientePF clienteTeste = new ClientePF(6, calendar.getTime(), "Cliente Teste", "12345678912", 123456789);
        clienteController.persistirBanco(clienteTeste, true, false, false);

        Produto produtoTeste = new Produto(5, "Produto Teste", (float) 6.0);
        produtoController.persistirBanco(produtoTeste, true, false, false);

        vendaTest = new Venda(5, calendar.getTime(), clienteTeste, (float) 1.0, (float) 15.0);
        vendaTest.adicionaItem(produtoTeste, 3);
    }

    @Test
    public void persistirVendaNoBancoTest() {
        vendaController.persistirBanco(vendaTest);

        Venda vendaPersistida = getVendaById(vendaController.getVendas(), vendaTest.getIdVenda());

        assertNotNull(vendaPersistida);
        assertEquals(vendaTest.getCliente().getNome(), vendaPersistida.getCliente().getNome());
        assertEquals(vendaTest.getValorPago(), vendaPersistida.getValorPago(), 0.001);
        assertEquals(vendaTest.getTotalVendaLiquida(), vendaPersistida.getTotalVendaLiquida(), 0.001);
    }

    @Test
    public void deletarVendaDoBancoTest() {
        vendaController.persistirBanco(vendaTest);

        Venda vendaPersistida = getVendaById(vendaController.getVendas(), vendaTest.getIdVenda());
        assertNotNull(vendaPersistida);

        vendaController.deletarVendaDoBanco(vendaPersistida);

        Venda vendaDeletada = getVendaById(vendaController.getVendas(), vendaTest.getIdVenda());
        assertNull(vendaDeletada);
    }

    @Test
    public void atualizarVendaNoBancoTest() {
        vendaController.persistirBanco(vendaTest);

        Venda vendaPersistida = getVendaById(vendaController.getVendas(), vendaTest.getIdVenda());
        assertNotNull(vendaPersistida);

        float novoValorPago = 20.0f;
        vendaPersistida.setValorPago(novoValorPago);

        vendaController.atualizarVendaNoBanco(vendaPersistida);

        Venda vendaAtualizada = getVendaById(vendaController.getVendas(), vendaTest.getIdVenda());
        assertEquals(novoValorPago, vendaAtualizada.getValorPago(), 0.001);
    }

    private Venda getVendaById(ArrayList<Venda> listaVenda, int id) {
        for (Venda venda : listaVenda) {
            if (venda.getIdVenda() == id) {
                return venda;
            }
        }
        return null;
    }

    public void limparBancoDados() {
        Connection conexao = new DAOConexaoDB().getConexao();
        String[] tabelas = {"tb_venda_item", "tb_venda", "tb_produto", "tb_cliente"};
        try {
            PreparedStatement disableFK = conexao.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.executeUpdate();
            disableFK.close();

            for (String tabela : tabelas) {
                PreparedStatement pst = conexao.prepareStatement("DELETE FROM " + tabela);
                pst.executeUpdate();
                pst.close();
            }

            PreparedStatement enableFK = conexao.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.executeUpdate();
            enableFK.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar banco de dados: " + e.getMessage(), e);
        }
    }
}
*/