package com.meuprojeto.controller;

import com.meuprojeto.model.Acesso;
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

    //Post recebe os dados de uma tela por exemplo
    @ResponseBody //Da o retorno da API
    @PostMapping(value ="/salvarAcesso") //Mapeando a url para receber json
    public ResponseEntity <Acesso> salvarAcesso(@RequestBody Acesso acesso) { //Recebe json e converte para objeto

        Acesso acessoSalvo = acessoService.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }
}
