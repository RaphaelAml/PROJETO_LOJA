package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.AcessoController;
import com.meuprojeto.model.Acesso;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProjetoLojaApplication.class)
public class ProjetoLojaApplicationTests extends TestCase {

    //@Autowired
    //private AcessoService acessoService;

    //@Autowired
    //private AcessoRepository acessoRepository;


    private AcessoController acessoController;

    @Test
    public void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        acesso = acessoController.salvarAcesso(acesso).getBody();

        assertEquals(true, acesso.getId() > 0);
    }

}
