package com.meuprojeto.projetoloja;

import controller.AcessoController;
import model.Acesso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProjetoLojaApplication.class)
public class ProjetoLojaApplicationTests {

    //@Autowired
    //private AcessoService acessoService;

    //@Autowired
    //private AcessoRepository acessoRepository;


    private AcessoController acessoController;

    @Test
    void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        acessoController.salvarAcesso(acesso);
    }

}
