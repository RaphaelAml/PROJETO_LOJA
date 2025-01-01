package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.PessoaController;
import com.meuprojeto.enums.TipoEndereco;
import com.meuprojeto.model.Endereco;
import com.meuprojeto.model.PessoaJuridica;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Calendar;

@Profile("test")
@SpringBootTest(classes = ProjetoLojaApplication.class)
public class TestePessoaUsuario extends TestCase {


    @Autowired
    private PessoaController pessoaController;


    @Test
    public void testCadPessoaFisica() throws ExcecaoMsgErro {


        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Raphael");
        pessoaJuridica.setEmail("raphaelaml02@gmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Alguma coisa");
        endereco1.setCep("86430000");
        endereco1.setComplemento("Alguma coisa");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("123");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogradouro("Rua alguma coisa");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Sap");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Alguma coisa");
        endereco2.setCep("86430000");
        endereco2.setComplemento("Alguma coisa");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("1111");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogradouro("Rua alguma coisa");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Curitiba");

        pessoaJuridica.getEnderecos().add(endereco1);
        pessoaJuridica.getEnderecos().add(endereco2);


        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();

        assertEquals(true, pessoaJuridica.getId() > 0 );

        for( Endereco endereco : pessoaJuridica.getEnderecos()) {
            assertEquals(true,endereco.getId() > 0);
        }

        assertEquals(2,pessoaJuridica.getEnderecos().size());

    }
}
