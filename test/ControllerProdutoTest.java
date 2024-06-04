import Controller.ControllerProduto;
import Model.Produto;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/*
import static org.junit.jupiter.api.Assertions.assertThrows;
*/
/**
 *
 * @author ian21
 */

public class ControllerProdutoTest {
private Produto produto;
private ControllerProduto controllerProduto;


@Before
public void setUp(){
    // Criação do novo produto de teste
    produto = new Produto(5, "Produto Teste", (float)6.0);
    controllerProduto = new ControllerProduto();
}

@Test
public void persistirBancoInsertTest(){

    // Inserir o produto no banco
    controllerProduto.persistirBanco(produto, true, false, false);

    // Verificar se o produto foi inserido
    Produto result = controllerProduto.getProduto(produto.getIdProduto());

    // Verificar se os dados estao corretos
    assertEquals(produto.getDescricao(), result.getDescricao());
    assertEquals(produto.getValor(), result.getValor(), 0.001);
}

@Test
public void persistirBancoUpdateTest(){

    // Atualizar a descrição do produto
    produto.setDescricao("Descricao Update");

    // Persistir a atualização no banco
    controllerProduto.persistirBanco(produto, false, true, false);

    // Verificar se o produto foi atualizado
    Produto result = controllerProduto.getProduto(produto.getIdProduto());

    // Verificar se os dados estao corretos
    assertNotNull(result);
    assertEquals(produto.getDescricao(), result.getDescricao());
}

@Test
public void persistirBancoDeleteTest(){

        // Excluir o produto do banco
        controllerProduto.persistirBanco(produto, false, false, true);

        // Verificar se foi excluído corretamente
        Produto result = controllerProduto.getProduto(produto.getIdProduto());

        // Verificar se o produto não existe mais
        assertNull(result);
}

//Testes de casos de borda:

@Test
public void testGetProdutoIdInexistente() {
  
    // Buscar produto com id inexistente
    Produto result = controllerProduto.getProduto(9999); 
    assertNull(result);
}
/*
@Test
public void testPersistirBancoFlagsInvalidas() {
    // Enviar flags inválidas
    Exception exception = assertThrows(IllegalArgumentException.class, () -> controllerProduto.persistirBanco(produto, true, true, true));
    assertEquals("Flags inválidas!", exception.getMessage());
}
*/
}
