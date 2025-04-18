package com.meuprojeto.projetoloja;

import com.meuprojeto.controller.PessoaController;
import com.meuprojeto.dto.ObjetoPostCarneJuno;
import com.meuprojeto.enums.TipoEndereco;
import com.meuprojeto.model.Endereco;
import com.meuprojeto.model.PessoaFisica;
import com.meuprojeto.model.PessoaJuridica;
import com.meuprojeto.repository.PessoaRepository;
import com.meuprojeto.service.ServiceJunoBoleto;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ServiceJunoBoleto serviceJunoBoleto;

    @Test
    public void testToke() throws Exception{
        String valor = serviceJunoBoleto.geraChaveBoletoPix();
        System.out.println(valor);
    }


    @Test
    public void testeToken() throws Exception {

        ObjetoPostCarneJuno objetoPostCarneJuno = new ObjetoPostCarneJuno();
        objetoPostCarneJuno.setDescription("Teste de geração de boleto e pix");
        objetoPostCarneJuno.setEmail("alex.fernando.egidio@gmail.com");
        objetoPostCarneJuno.setIdVenda(18L);
        objetoPostCarneJuno.setInstallments("6");
        objetoPostCarneJuno.setPayerCpfCnpj("05916564937");
        objetoPostCarneJuno.setPayerName("Alex fernando");
        objetoPostCarneJuno.setPayerPhone("45999795800");
        objetoPostCarneJuno.setReference("Venda de venda de loja virtual cod: 18");
        objetoPostCarneJuno.setTotalAmount("50.00");
        String valor =	serviceJunoBoleto.gerarCarneApi(objetoPostCarneJuno);
        System.out.println(valor);
    }


    @Test
    public void testCadPessoaJuridica() throws ExcecaoMsgErro {


        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Raphael");
        pessoaJuridica.setEmail("raphaelaml02@gmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556564656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Jd Dias");
        endereco1.setCep("556556565");
        endereco1.setComplemento("Casa cinza");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("389");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogradouro("Av. são joao sexto");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Curitiba");


        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Jd Maracana");
        endereco2.setCep("7878778");
        endereco2.setComplemento("Andar 4");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("555");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogradouro("Av. maringá");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Curitiba");

        pessoaJuridica.getEnderecos().add(endereco2);
        pessoaJuridica.getEnderecos().add(endereco1);


        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();

        assertEquals(true, pessoaJuridica.getId() > 0 );

        for (Endereco endereco : pessoaJuridica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaJuridica.getEnderecos().size());


    }


    @Test
    public void testCadPessoaFisica() throws ExcecaoMsgErro {

        PessoaJuridica pessoaJuridica =  pessoaRepository.existeCnpjCadastrado("1735727159893");

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("965.009.350-80");
        pessoaFisica.setNome("Raphael");
        pessoaFisica.setEmail("raphaelaml02@gmadil.com");
        pessoaFisica.setTelefone("45999795800");
        pessoaFisica.setEmpresa(pessoaJuridica);

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Jd Dias");
        endereco1.setCep("556556565");
        endereco1.setComplemento("Casa cinza");
        endereco1.setNumero("389");
        endereco1.setPessoa(pessoaFisica);
        endereco1.setRuaLogradouro("Av. são joao sexto");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Curitiba");
        endereco1.setEmpresa(pessoaJuridica);


        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Jd Maracana");
        endereco2.setCep("7878778");
        endereco2.setComplemento("Andar 4");
        endereco2.setNumero("555");
        endereco2.setPessoa(pessoaFisica);
        endereco2.setRuaLogradouro("Av. maringá");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Curitiba");
        endereco2.setEmpresa(pessoaJuridica);

        pessoaFisica.getEnderecos().add(endereco2);
        pessoaFisica.getEnderecos().add(endereco1);


        pessoaFisica = pessoaController.salvarPf(pessoaFisica).getBody();

        assertEquals(true, pessoaFisica.getId() > 0 );

        for (Endereco endereco : pessoaFisica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaFisica.getEnderecos().size());


    }


}
