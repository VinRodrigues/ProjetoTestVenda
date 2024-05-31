/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

/**
 *
 * @author ian21
 */
import Controller.ControllerVenda;
import Model.ClienteCompra;
import Model.ItemVendido;
import Model.Venda;
import Model.ClientePF;
import Model.DAO.DAOVenda;
import Model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerVendaTest {

    @InjectMocks
    private ControllerVenda controllerVenda;

    @Mock
    private DAOVenda daoVenda;

    private ClientePF clientePF1;
    private ClientePF clientePF2;
    private Venda venda1;
    private Venda venda2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        clientePF1 = new ClientePF(1, new Date(), "Cliente 1", "12345678901", 111111);
        clientePF2 = new ClientePF(2, new Date(), "Cliente 2", "10987654321", 222222);
        venda1 = new Venda(1, new Date(), clientePF1, 10.0f, 100.0f);
        venda2 = new Venda(2, new Date(), clientePF2, 5.0f, 50.0f);
    }

    @Test
    public void testGetVendas() {
        ArrayList<Venda> vendas = new ArrayList<>();
        vendas.add(venda1);
        vendas.add(venda2);

        when(daoVenda.getAll()).thenReturn(vendas);

        ArrayList<Venda> result = controllerVenda.getVendas();
        assertEquals(2, result.size());
        assertTrue(result.contains(venda1));
        assertTrue(result.contains(venda2));
    }

    @Test
    public void testPersistirBanco() {
        doNothing().when(daoVenda).insert(any(Venda.class));

        controllerVenda.persistirBanco(venda1);

        verify(daoVenda, times(1)).insert(venda1);
    }

    @Test
    public void testGetClientePFMaisComprou() {
        ArrayList<ClienteCompra> clienteCompras = new ArrayList<>();
        clienteCompras.add(new ClienteCompra(clientePF1, 2));

        when(daoVenda.getClientePFMaisComprou()).thenReturn(clienteCompras);

        ArrayList<ClienteCompra> result = controllerVenda.getClientePFMaisComprou();
        assertEquals(1, result.size());
        assertEquals(clientePF1.getIdCliente(), result.get(0).getCliente().getIdCliente());
    }

    @Test
    public void testGetClientePJMenosComprou() {
        ArrayList<ClienteCompra> clienteCompras = new ArrayList<>();
        clienteCompras.add(new ClienteCompra(clientePF2, 1));

        when(daoVenda.getClientePJMenosComprou()).thenReturn(clienteCompras);

        ArrayList<ClienteCompra> result = controllerVenda.getClientePJMenosComprou();
        assertEquals(1, result.size());
        assertEquals(clientePF2.getIdCliente(), result.get(0).getCliente().getIdCliente());
    }

    @Test
    public void testGetProdutosVendidos() {
        ArrayList<ItemVendido> itensVendidos = new ArrayList<>();
        itensVendidos.add(new ItemVendido(new Produto(1, "Produto 1", 10.0f), 10));

        when(daoVenda.getProdutosVendidos()).thenReturn(itensVendidos);

        ArrayList<ItemVendido> result = controllerVenda.getProdutosVendidos();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testToString() {
        ArrayList<Venda> vendas = new ArrayList<>();
        vendas.add(venda1);

        when(daoVenda.getAll()).thenReturn(vendas);

        String result = controllerVenda.toString();
        assertTrue(result.contains("Código Venda: 1"));
    }
}


