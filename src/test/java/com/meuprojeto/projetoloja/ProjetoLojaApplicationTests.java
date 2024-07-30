package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.AcessoController;
import com.meuprojeto.model.Acesso;
import org.junit.jupiter.api.Test;
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
