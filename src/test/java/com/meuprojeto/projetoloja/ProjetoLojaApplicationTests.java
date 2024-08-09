package com.meuprojeto.projetoloja;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meuprojeto.controller.AcessoController;
import com.meuprojeto.model.Acesso;
import com.meuprojeto.repository.AcessoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;

import static junit.framework.TestCase.assertEquals;


@Profile("teste")
@SpringBootTest
public class ProjetoLojaApplicationTests {

    @Autowired
    private AcessoController acessoController;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private WebApplicationContext wac;

    /*Teste do endpoint salvar*/
    @Test
    public void testResApiCadastroAcesso() throws JsonProcessingException, Exception {

        /*Objetos que serão resposaveis para fazer os testes, requisições para API e dar um  retorno*/
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("Teste de mock");

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                                        .perform(MockMvcRequestBuilders.post("/salvarAcesso")
                                        .content(objectMapper.writeValueAsString(acesso))
                                        .accept(MediaType.APPLICATION_JSON)
                                                .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno da API" + retornoApi.andReturn().getResponse().getContentAsString());
        /*Converte o retorno da API para um objeto de acesso*/

        Acesso objetoRetorno = objectMapper.
                                        readValue( retornoApi.andReturn().getResponse().getContentAsString(),
                                        Acesso.class);

        assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
    }

    @Test
    public void testResApiDeleteAcesso() throws JsonProcessingException, Exception {

        /*Objetos que serão resposaveis para fazer os testes, requisições para API e dar um  retorno*/
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("Teste de mock delete");

        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.post("/deleteAcesso")
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
        System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

        assertEquals("Acesso removido",retornoApi.andReturn().getResponse().getContentAsString());
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }

    @Test
    public void testResApiDeletePorIdAcesso() throws JsonProcessingException, Exception {

        /*Objetos que serão resposaveis para fazer os testes, requisições para API e dar um  retorno*/
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        //Cria um novo objeto Acesso e salva no repositorio
        Acesso acesso = new Acesso();

        acesso.setDescricao("Teste de mock delete ID");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        // Realizar a requisiçao para deletar o acesso pelo ID /*Envia e da o retorno*/
        ResultActions retornoApi = mockMvc
                        .perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/" + acesso.getId())
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // Imprimi a resposta e o status
        System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
        System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

        // Verifica se a resposta e o status estao corretos
        assertEquals("Acesso removido",retornoApi.andReturn().getResponse().getContentAsString());
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }

    @Test
    public void testResApiObterAcessoID() throws JsonProcessingException, Exception {

        /*Objetos que serão resposaveis para fazer os testes, requisições para API e dar um  retorno*/ /*Envia e da o retorno*/
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        //Cria um novo objeto Acesso e salva no repositorio
        Acesso acesso = new Acesso();

        acesso.setDescricao("Teste de mock Obter ID");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        // Realizar a requisiçao para deletar o acesso pelo ID /*Envia e da o retorno*/
        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.get("/obterAcesso/" + acesso.getId())
                        .content(objectMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));


        // Verifica se a resposta e o status estao corretos
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        // Converte para um objeto para se realmente está consultando por ID
        Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);

        assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
        assertEquals(acesso.getId(), acessoRetorno.getId());
    }

    @Test
    public void testResApiObterAcessoDesc() throws JsonProcessingException, Exception {

        /*Objetos que serão responsáveis para fazer os testes, requisições para API e dar um retorno*/
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        // Cria um novo objeto Acesso e salva no repositório
        Acesso acesso = new Acesso();
        acesso.setDescricao("Teste de mock Obter List");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        // Envia e dá o retorno
        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.get("/buscarPorDesc/" + acesso.getDescricao())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // Verifica se a resposta e o status estão corretos
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        List<Acesso> retornoApiList = objectMapper
                .readValue(retornoApi.andReturn().getResponse().getContentAsString(),
                        new TypeReference<List<Acesso>>() {});

        assertEquals(1, retornoApiList.size());
        assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());

        acessoRepository.deleteById(acesso.getId());
    }




}
