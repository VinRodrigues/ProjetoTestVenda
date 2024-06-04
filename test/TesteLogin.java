/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void TesteParavalidarLoginOperadorTest(){
        String login = "paulo";
        String senha = "teste";
        String priv = "Operador";
        String nome = "Paulo da Silva";
        Login teste = new Login(login, senha, nome, priv);
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se os dados de login são os esperados
        assertEquals(teste.getNome(), result.getNome());
        assertEquals(teste.getLogin(), result.getLogin());
        assertEquals(teste.getSenha(), result.getSenha());
        assertEquals(teste.getPrivilegio(), result.getPrivilegio());
        System.out.println("TesteParavalidarLoginOperadorTest: OK");
    }

    @Test
    public void TesteParavalidarLoginAdministradorTest(){
        String login = "christian";
        String senha = "teste";
        String priv = "Administrador";
        String nome = "Christian Adriano";
        Login teste = new Login(login, senha, nome, priv);
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se os dados de login são os esperados
        assertEquals(teste.getNome(), result.getNome());
        assertEquals(teste.getLogin(), result.getLogin());
        assertEquals(teste.getSenha(), result.getSenha());
        assertEquals(teste.getPrivilegio(), result.getPrivilegio());
        System.out.println("TesteParavalidarLoginAdministradorTest: OK");
    }

    @Test
    public void TesteParavalidarLoginInvalidoTest(){
        String login = "invalido";
        String senha = "senhaerrada";
        String priv = "Usuario";
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se o login inválido retorna null
        assertEquals(null, result); // Teste falha se o método não retorna null
        System.out.println("TesteParavalidarLoginInvalidoTest: OK");
    }

    @Test
    public void TesteParavalidarLoginComSenhaInvalidaTest(){
        String login = "paulo";
        String senha = "senhaerrada";
        String priv = "Operador";
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se o login com senha inválida retorna null
        assertEquals(null, result); // Teste falha se o método não retorna null
        System.out.println("TesteParavalidarLoginComSenhaInvalidaTest: OK");
    }

    @Test
    public void TesteParavalidarLoginComPrivilegioInvalidoTest(){
        String login = "paulo";
        String senha = "teste";
        String priv = "SuperUsuario";
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se o login com privilégio inválido retorna null
        assertEquals(null, result); // Teste falha se o método não retorna null
        System.out.println("TesteParavalidarLoginComPrivilegioInvalidoTest: OK");
    }

   

    @Test
    public void TesteParavalidarLoginVazioTest(){
        String login = "";
        String senha = "teste";
        String priv = "Operador";
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se o login vazio retorna null
        assertEquals(null, result); // Teste falha se o método não retorna null
        System.out.println("TesteParavalidarLoginVazioTest: OK");
    }
    
    @Test
    public void TesteParavalidarSenhaVaziaTest(){
        String login = "paulo";
        String senha = "";
        String priv = "Operador";
        Login result = loginController.validaLogin(login, senha, priv);
        
        // Conferir se a senha vazia retorna null
        assertEquals(null, result); // Teste falha se o método não retorna null
        System.out.println("TesteParavalidarSenhaVaziaTest: OK");
    }
}
