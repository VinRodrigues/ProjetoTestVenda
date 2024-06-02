import Controller.ControllerProduto;
import Model.Produto;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 * @author giova
 */
public class ControllerProdutoTest {
    private Produto produto;
    private ControllerProduto produtoController;
            
    @Before
    public void setUp(){
        produto = new Produto(5, "Teste Inserir", (float)6.0);
        produtoController = new ControllerProduto();
        produtoController.persistirBanco(produto, true, false, false);
    }
    
    @Test
    public void persistirBancoInsertTest(){
        
        // Buscar o produto do banco para verificar se foi inserido corretamente
        Produto result = produtoController.getProduto(produto.getIdProduto());
        
        // Verificar se os dados do produto são os esperados
        assertEquals(produto.getDescricao(), result.getDescricao());
        assertEquals(produto.getValor(), result.getValor(), 0.001);
    }
    
    @Test
    public void persistirBancoUpdateTest(){
        produto.setDescricao("Teste Update");
        produtoController.persistirBanco(produto, false, true, false);
        
        // Buscar o produto do banco para verificar se foi inserido corretamente
        Produto result = produtoController.getProduto(produto.getIdProduto());
        
        // Verificar se os dados do produto são os esperados
        assertNotNull(result);
        assertEquals(produto.getDescricao(), result.getDescricao());
    }
    
    @Test
    public void persistirBancoDeleteTest(){
        produtoController.persistirBanco(produto, false, false, true);
        
        // Buscar o produto do banco para verificar se foi inserido corretamente
        Produto result = produtoController.getProduto(produto.getIdProduto());
        
        // Verificar se o produto não existe mais
        assertNull(result);
    }
}