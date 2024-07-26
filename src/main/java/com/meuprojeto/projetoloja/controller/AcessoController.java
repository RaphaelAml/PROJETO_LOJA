package com.meuprojeto.projetoloja.controller;

import com.meuprojeto.projetoloja.model.Acesso;
import com.meuprojeto.projetoloja.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping("/salvarAcesso")
    public Acesso salvarAcesso(Acesso acesso) {

        return acessoService.save(acesso);
    }
}
