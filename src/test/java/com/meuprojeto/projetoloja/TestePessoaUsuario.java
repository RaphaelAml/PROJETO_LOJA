package com.meuprojeto.projetoloja;

import com.meuprojeto.model.PessoaFisica;
import com.meuprojeto.model.PessoaJuridica;
import com.meuprojeto.repository.PesssoaRepository;
import com.meuprojeto.service.PessoaUserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest(classes = ProjetoLojaApplication.class)
public class TestePessoaUsuario extends TestCase {


    @Autowired
    private PessoaUserService pessoaUserService;

    @Autowired
    private PesssoaRepository pesssoaRepository;


    @Test
    public void testCadPessoaFisica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("865545598956556");
        pessoaJuridica.setNome("Raphael Solution");
        pessoaJuridica.setEmail("raphaelaml1@outlook.com");
        pessoaJuridica.setTelefone("43999999999");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        pesssoaRepository.save(pessoaJuridica);

        /*
		PessoaFisica pessoaFisica = new PessoaFisica();

		pessoaFisica.setCpf("0597975788");
		pessoaFisica.setNome("Raphael");
		pessoaFisica.setEmail("raphaelaml1@outlook.com");
		pessoaFisica.setTelefone("45999795800");
		pessoaFisica.setEmpresa(pessoaFisica);*/

    }


}
