package com.meuprojeto.controller;

import com.meuprojeto.model.Acesso;
import com.meuprojeto.repository.AcessoRepository;
import com.meuprojeto.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController //recebe o json e retorna
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    AcessoRepository acessoRepository;

    //Post recebe os dados de uma tela por exemplo
    @ResponseBody //Da o retorno da API
    @PostMapping(value = "/salvarAcesso") //Mapeando a url para receber json //endpoint
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { //Recebe json e converte para objeto

        Acesso acessoSalvo = acessoService.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ResponseBody //Da o retorno da API
    @PostMapping(value = "/deleteAcesso") //Mapeando a url para receber json
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { //Recebe json e converte para objeto

        acessoRepository.deleteById(acesso.getId());

        /*Isso é um retorno de API, O boby vai mostrar essa frase dentro do postman*/
        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }

}
