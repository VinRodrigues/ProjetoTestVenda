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
public void TesteParaInserirBancoTest(){

    // Inserir o produto no banco
    controllerProduto.persistirBanco(produto, true, false, false);

    // Verificar se o produto foi inserido
    Produto result = controllerProduto.getProduto(produto.getIdProduto());

    // Verificar se os dados estao corretos
    assertEquals(produto.getDescricao(), result.getDescricao());
    assertEquals(produto.getValor(), result.getValor(), 0.001);
    System.out.println("TesteParaInserirBancoTest: OK");
}

@Test
public void TesteParaAtualizarBancoTest(){

    // Atualizar a descrição do produto
    produto.setDescricao("Descricao Update");

    // Persistir a atualização no banco
    controllerProduto.persistirBanco(produto, false, true, false);

    // Verificar se o produto foi atualizado
    Produto result = controllerProduto.getProduto(produto.getIdProduto());

    // Verificar se os dados estao corretos
    if (result != null) {
        assertEquals(produto.getDescricao(), result.getDescricao());
        System.out.println("TesteParaAtualizarBancoTest: OK");
    } else {
        // Se for null
        System.out.println("TesteParaAtualizarBancoTest Error: Produto não encontrado");
    }
}

@Test
public void TesteParaExcluirBancoTest(){

        // Excluir o produto do banco
        controllerProduto.persistirBanco(produto, false, false, true);

        // Verificar se foi excluído corretamente
        Produto result = controllerProduto.getProduto(produto.getIdProduto());

        // Verificar se o produto não existe mais
        assertNull(result);
        System.out.println("TesteParaExcluirBancoTest: OK");
}

//Testes de casos de borda:

@Test
public void TesteParaBuscarInexistenteTest() {
  
    // Buscar produto com id inexistente
    Produto result = controllerProduto.getProduto(9999); 
    assertNull(result);
    System.out.println("TesteParaBuscarInexistente: OK");
}

}
