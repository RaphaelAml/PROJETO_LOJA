package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.AcessoController;
import com.meuprojeto.model.Acesso;
import com.meuprojeto.repository.AcessoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ProjetoLojaApplicationTests {

    @Autowired
    private AcessoController acessoController;

    @Autowired
    private AcessoRepository acessoRepository;

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


        /*Teste de carregamento*/
        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

        assertEquals(acesso.getId(), acesso2.getId());


        /*Teste de delete*/
        acessoRepository.deleteById(acesso2.getId());

        acessoRepository.flush(); /*Roda o SQL de delete no banco de dados*/

        Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);

        assertEquals(true, acesso3 == null);

        /*Teste de query*/
        acesso = new Acesso();

        acesso.setDescricao("Teste");

        acesso = acessoController.salvarAcesso(acesso).getBody();

        List<Acesso> acessos = acessoRepository.buscarAcessoDesc("Tes".trim().toUpperCase());

        assertEquals(1,acessos.size());

        acessoRepository.deleteById(acesso.getId());

    }
}
