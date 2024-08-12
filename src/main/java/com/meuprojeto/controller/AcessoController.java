package com.meuprojeto.controller;

import com.meuprojeto.model.Acesso;
import com.meuprojeto.repository.AcessoRepository;
import com.meuprojeto.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "https://www.alguma coisa") /*Validação por exemplo só é liberado se a requisão vim de algum servidor autorizado*/

@Controller
@RestController //recebe o json e retorna
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    AcessoRepository acessoRepository;

    /*Metodo*/
    //Post recebe os dados de uma tela por exemplo
    @ResponseBody //Da o retorno da API
    @PostMapping(value = "/salvarAcesso") //Mapeando a url para receber json //endpoint
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { //Recebe json e converte para objeto

        Acesso acessoSalvo = acessoService.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
        /*Salva e retorna um Json*/
    }

    //@Secured({"ROLE_GERENTE", "ROLE_ADMIN"}) /*Somente quem tem esses perfil pode deletar acesso*/
    /*Metodo*/
    @ResponseBody //Da o retorno da API
    @PostMapping(value = "/deleteAcesso") //Mapeando a url para receber json
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { //Recebe json e converte para objeto

        acessoRepository.deleteById(acesso.getId());

        /*Isso é um retorno de API, O boby vai mostrar essa frase dentro do postman*/
        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }

    /*Metodo*/
    @ResponseBody
    @DeleteMapping(value = "/deleteAcessoPorId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        acessoRepository.deleteById(id);


        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }

    /*Metodo*/
    @ResponseBody
    @GetMapping(value = "/obterAcesso/{id}")
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {

        Acesso acesso = acessoRepository.findById(id).get();

        return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
    }


    /*Metodo*/
    @ResponseBody
    @GetMapping(value = "/buscarPorDesc/{desc}")
    public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {
        List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc.trim().toUpperCase());
        return new ResponseEntity<>(acesso, HttpStatus.OK);
    }


}
