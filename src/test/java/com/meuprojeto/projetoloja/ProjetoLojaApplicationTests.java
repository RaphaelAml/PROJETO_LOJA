package com.meuprojeto.projetoloja;

import com.meuprojeto.projetoloja.model.Acesso;
import com.meuprojeto.projetoloja.repository.AcessoRepository;
import com.meuprojeto.projetoloja.service.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProjetoLojaApplication.class)
public class ProjetoLojaApplicationTests {

    @Autowired
    private AcessoService acessoService;

    //@Autowired
    //private AcessoRepository acessoRepository;

    @Test
    void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        acessoService.save(acesso);
    }

}
