/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

/**
 *
 * @author ian21
 */

import Controller.ControllerProduto;
import Model.DAO.DAOProduto;
import Model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerProdutoTest {

    @Mock
    private DAOProduto daoProduto;

    @InjectMocks
    private ControllerProduto controllerProduto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProdutos() {
        List<Produto> mockProdutos = new ArrayList<>();
        mockProdutos.add(new Produto(1, "Produto 1", 10.0f));
        mockProdutos.add(new Produto(2, "Produto 2", 20.0f));

        when(daoProduto.getAll()).thenReturn((ArrayList<Produto>) mockProdutos);

        ArrayList<Produto> produtos = controllerProduto.getProdutos();

        assertEquals(2, produtos.size());
        assertEquals("Produto 1", produtos.get(0).getDescricao());
        assertEquals("Produto 2", produtos.get(1).getDescricao());

        verify(daoProduto, times(1)).getAll();
    }

    @Test
    public void testPersistirBanco_Insert() {
        Produto produto = new Produto(1, "Produto 1", 10.0f);

        controllerProduto.persistirBanco(produto, true, false, false);

        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);

        verify(daoProduto, times(1)).insert(captor.capture());
        verify(daoProduto, times(1)).setCodigoBancoProduto(captor.capture());

        Produto capturedProduto = captor.getValue();
        assertEquals("Produto 1", capturedProduto.getDescricao());
    }

    @Test
    public void testPersistirBanco_Edit() {
        Produto produto = new Produto(1, "Produto 1", 10.0f);

        controllerProduto.persistirBanco(produto, false, true, false);

        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);

        verify(daoProduto, times(1)).update(captor.capture());

        Produto capturedProduto = captor.getValue();
        assertEquals("Produto 1", capturedProduto.getDescricao());
    }

    @Test
    public void testPersistirBanco_Delete() {
        Produto produto = new Produto(1, "Produto 1", 10.0f);

        controllerProduto.persistirBanco(produto, false, false, true);

        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);

        verify(daoProduto, times(1)).delete(captor.capture());

        Produto capturedProduto = captor.getValue();
        assertEquals("Produto 1", capturedProduto.getDescricao());
    }

    @Test
    public void testGetProduto() {
        Produto mockProduto = new Produto(1, "Produto 1", 10.0f);

        when(daoProduto.getProduto(1)).thenReturn(mockProduto);

        Produto produto = controllerProduto.getProduto(1);

        assertEquals("Produto 1", produto.getDescricao());
        assertEquals(10.0f, produto.getValor(), 0.01);

        verify(daoProduto, times(1)).getProduto(1);
    }

    @Test
    public void testToString() {
        List<Produto> mockProdutos = new ArrayList<>();
        mockProdutos.add(new Produto(1, "Produto 1", 10.0f));
        mockProdutos.add(new Produto(2, "Produto 2", 20.0f));

        when(daoProduto.getAll()).thenReturn((ArrayList<Produto>) mockProdutos);

        String expected = "1 - Produto 1\n2 - Produto 2\n";
        String actual = controllerProduto.toString();

        assertEquals(expected, actual);

        verify(daoProduto, times(1)).getAll();
    }
}
