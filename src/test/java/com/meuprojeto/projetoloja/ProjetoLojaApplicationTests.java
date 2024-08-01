package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.AcessoController;
import com.meuprojeto.model.Acesso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ProjetoLojaApplicationTests {

    @Autowired
    private AcessoController acessoController;

    @Test
    public void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        assertEquals(true, acesso.getId() == null);

        /*Grava no banco de dados*/
        acesso = acessoController.salvarAcesso(acesso).getBody();

        assertEquals(true, acesso.getId() > 0);

        /*Validar dados salvos da forma correta*/
        assertEquals("ROLE_ADMIN", acesso.getDescricao());
    }
}
