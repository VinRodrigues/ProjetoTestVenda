import Controller.ControllerLogin;
import Model.Login;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * author vinicius
 */
public class TesteLogin {
    private final ControllerLogin loginController = new ControllerLogin();
    
    @Test
    public void validaLoginOperadorTest(){
        String login = "paulo";
        String senha = "teste";
        String priv = "Operador";
        String nome = "Paulo da Silva";
        Login teste = new Login(login, senha, nome, priv);
        Login result = loginController.validaLogin(login, senha, priv);
        
        //Conferir se os dados de login são os esperados
        assertEquals(teste.getNome(), result.getNome());
        assertEquals(teste.getLogin(), result.getLogin());
        assertEquals(teste.getSenha(), result.getSenha());
        assertEquals(teste.getPrivilegio(), result.getPrivilegio());
    }

    @Test
    public void validaLoginAdministradorTest(){
        String login = "christian";
        String senha = "teste";
        String priv = "Administrador";
        String nome = "Christian Adriano";
        Login teste = new Login(login, senha, nome, priv);
        Login result = loginController.validaLogin(login, senha, priv);
        
        //Conferir se os dados de login são os esperados
        assertEquals(teste.getNome(), result.getNome());
        assertEquals(teste.getLogin(), result.getLogin());
        assertEquals(teste.getSenha(), result.getSenha());
        assertEquals(teste.getPrivilegio(), result.getPrivilegio());
    }
}
